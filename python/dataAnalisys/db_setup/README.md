# Student Data Database Setup

This guide provides step-by-step instructions to spin up the PostgreSQL database containing the optimal schema for the student academic and mental health dataset.

## Schema Overview
The design normalizes the raw dataset into three logical tables:
1. `courses`: A dimension table for student courses (reduces duplicate string inputs).
2. `students`: Core student demographic information (age, gender, year).
3. `student_metrics`: A 1-to-1 table linking to `students` that stores all continuous and qualitative mental health and academic measurements (stress levels, screen time, CGPA, etc.). 

## Initialization via Docker Compose

### Prerequisites
- Docker and Docker Compose installed on your system.

### Steps
1. Navigate to your project directory (if not already there):
   ```bash
   cd /home/s-cesar/codes/pratical_study/python/dataAnalisys
   ```

2. Run Docker Compose to start the PostgreSQL container:
   ```bash
   docker-compose up -d
   ```
   *Note: This will download PostgreSQL, start a container named `student_data_db` on port 5432, and automatically execute the `db_setup/schema.sql` script to create the tables during first-time initialization.*

3. Verify the container is running:
   ```bash
   docker ps
   ```

4. Connect to the database visually (via DBeaver/pgAdmin/VS Code) or using the CLI:

   **CLI connection:**
   ```bash
   docker exec -it student_data_db psql -U admin -d student_data
   ```

   **Connection Settings for External Tools:**
   - **Host:** localhost (or 127.0.0.1)
   - **Port:** 5432
   - **Database:** student_data
   - **Username:** admin
   - **Password:** adminpassword

### Cleaning Up
To stop the database and remove the container, run:
```bash
docker-compose down
```
If you also want to delete the persistent database volume (wiping the data):
```bash
docker-compose down -v
```
