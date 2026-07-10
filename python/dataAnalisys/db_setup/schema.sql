CREATE TABLE courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE students (
    student_id VARCHAR(50) PRIMARY KEY,
    course_id INT NOT NULL,
    age INT CHECK (age >= 10 AND age <= 120),
    gender VARCHAR(10) CHECK (gender IN ('Male', 'Female', 'Other')),
    year_of_study INT CHECK (year_of_study >= 0 AND year_of_study <= 10),       -- cuidado que aqui esta em um formato meio estranho(1st, 2st,...)
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE RESTRICT
);


-- 1-to-1 relationship to students
CREATE TABLE student_metrics (
    student_id VARCHAR(50) PRIMARY KEY,
    
    -- Time-related metrics (hours per day)
    daily_study_hours NUMERIC(3, 1) CHECK (daily_study_hours >= 0),
    daily_sleep_hours NUMERIC(3, 1) CHECK (daily_sleep_hours >= 0),
    screen_time_hours NUMERIC(3, 1) CHECK (screen_time_hours >= 0),
    physical_activity_hours NUMERIC(2, 1) CHECK (physical_activity_hours >= 0),

    -- Scoring/Scales metrics
    stress_level VARCHAR(50) CHECK (stress_level IN ('low', 'medium', 'high')),
    anxiety_score INT CHECK (anxiety_score >= 0 AND anxiety_score <= 10),
    depression_score INT CHECK (depression_score >= 0 AND depression_score <= 10),
    academic_pressure_score INT CHECK (academic_pressure_score >= 0 AND academic_pressure_score <= 10),
    financial_stress_score INT CHECK (financial_stress_score >= 0 AND financial_stress_score <= 10),
    social_support_score INT CHECK (social_support_score >= 0 AND social_support_score <= 10),
    
    -- Categories & Qualitative
    sleep_quality VARCHAR(50) CHECK (sleep_quality IN ('poor', 'average', 'good')),  
    internet_quality VARCHAR(50) CHECK (internet_quality IN ('poor', 'average', 'good')),   
    burnout_level VARCHAR(50) CHECK (burnout_level IN ('low', 'medium', 'high')),      
    
    -- Academic Performance
    cgpa NUMERIC(4, 2) CHECK (cgpa BETWEEN 0.0 AND 10.0),
    attendance_percentage NUMERIC(4, 2) CHECK (attendance_percentage BETWEEN 0.0 AND 100.0),
    
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Optional: Create indexes for frequently queried columns
CREATE INDEX idx_students_course ON students(course_id);
CREATE INDEX idx_metrics_stress ON student_metrics(stress_level);
CREATE INDEX idx_metrics_burnout ON student_metrics(burnout_level);
