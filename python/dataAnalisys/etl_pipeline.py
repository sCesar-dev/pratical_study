import os
import logging
import pandas as pd
from sqlalchemy import create_engine, MetaData
from sqlalchemy.dialects.postgresql import insert
#student_id,age,gender,course,year,daily_study_hours,daily_sleep_hours,screen_time_hours,stress_level,anxiety_score,depression_score,academic_pressure_score,
#financial_stress_score,social_support_score,physical_activity_hours,sleep_quality,attendance_percentage,cgpa,internet_quality,burnout_level
#100001,23,Male,BTech,1st,4.3,6.8,6.1,High,10,3,4,2,6,1.8,Average,66.5,9.63,Good,High

# Setup Logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

# Configurations
DB_URL = os.getenv('DB_URL', 'postgresql://admin:adminpassword@localhost:5432/student_data')
CSV_PATH = 'datasets/student_mental_health_burnout.csv'
CHUNK_SIZE = 10000

engine = create_engine(DB_URL)
metadata = MetaData()


def init_db_metadata():
    """Reflect database tables to ensure they exist before inserting data."""
    try:
        metadata.reflect(bind=engine)
        logger.info("Database tables reflected successfully.")
        return True
    except Exception as e:
        logger.error(f"Failed to connect to database or reflect tables: {e}")
        return False


def clean_chunk(df):
    """Perform preliminary data cleaning on the dataframe chunk."""
    # Handle missing numerical values with 0
    numeric_cols = df.select_dtypes(include=['number']).columns
    df[numeric_cols] = df[numeric_cols].fillna(0)
    
    # Handle missing categorical values and standardize
    if 'gender' in df.columns:
        df['gender'] = df['gender'].str.title().fillna('Unknown')
    
    if 'course' in df.columns:
        df['course'] = df['course'].str.upper().fillna('UNKNOWN')
    
    if 'year' in df.columns:
        # Extract integer class level from "1st", "2nd", etc.
        df['year'] = df['year'].astype(str).str.extract(r'(\d+)').fillna(0).astype(int)
    
    return df


def insert_courses(df):
    """Returns a map of course_name -> course_id."""
    courses_table = metadata.tables.get('courses')
    unique_courses = df['course'].dropna().unique()
    
    with engine.connect() as conn:
        for course in unique_courses:
            ins = insert(courses_table).values(course_name=course)
            ins = ins.on_conflict_do_nothing(index_elements=['course_name'])
            conn.execute(ins)
        conn.commit()
    
    # Retrieve lookup map
    courses_map = {}
    with engine.connect() as conn:
        res = conn.execute(courses_table.select()).fetchall()
        for row in res:
            # Depending on SQLAlchemy 2.0 version, rows can be accessed via attribute `row.course_name` or tuple `row[1]`
            # Safe way is to use subscripting with the column name or attribute access.
            courses_map[row.course_name] = row.course_id
            
    return courses_map


def process_chunk(chunk):
    # 1. Clean the chunk
    df = clean_chunk(chunk)
    
    # 2. Insert and Get Courses Mapping
    courses_map = insert_courses(df)
    df['course_id'] = df['course'].map(courses_map)
    
    # Drop records that we couldn't map courses for (precautionary)
    df = df.dropna(subset=['course_id'])
    
    # 3. Prepare students DataFrame
    students_df = df[['student_id', 'course_id', 'age', 'gender', 'year']].copy()
    students_df = students_df.rename(columns={'year': 'year_of_study'})
    
    # 4. Prepare student_metrics DataFrame
    metrics_cols = [
        'student_id', 'daily_study_hours', 'daily_sleep_hours', 'screen_time_hours',
        'physical_activity_hours', 'stress_level', 'anxiety_score', 'depression_score',
        'academic_pressure_score', 'financial_stress_score', 'social_support_score',
        'sleep_quality', 'internet_quality', 'burnout_level', 'cgpa', 'attendance_percentage'
    ]
    metrics_df = df[metrics_cols]
    
    # 5. Insert using multi insert with conflict resolution
    students_table = metadata.tables.get('students')
    student_metrics_table = metadata.tables.get('student_metrics')
    
    with engine.connect() as conn:
        # Insert students
        stmt_students = insert(students_table).values(students_df.to_dict(orient='records'))
        stmt_students = stmt_students.on_conflict_do_nothing(index_elements=['student_id'])
        conn.execute(stmt_students)
        
        # Insert metrics
        stmt_metrics = insert(student_metrics_table).values(metrics_df.to_dict(orient='records'))
        stmt_metrics = stmt_metrics.on_conflict_do_nothing(index_elements=['student_id'])
        conn.execute(stmt_metrics)
        
        conn.commit()


def run_pipeline():
    logger.info("Starting ETL Pipeline...")
    
    if not os.path.exists(CSV_PATH):
        logger.error(f"Dataset not found at: {CSV_PATH}")
        return

    if not init_db_metadata():
        return

    try:
        chunk_iter = pd.read_csv(CSV_PATH, chunksize=CHUNK_SIZE)
        for i, chunk in enumerate(chunk_iter, start=1):
            logger.info(f"Processing chunk {i}...")
            process_chunk(chunk)
    
        logger.info("ETL Pipeline completed successfully!")
    except Exception as e:
        logger.error(f"Error during ETL process: {str(e)}")

        
if __name__ == "__main__":
    run_pipeline()
