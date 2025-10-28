# University Portal — Database Schema

SQL schema and sample data for the `university_portal` database:

```sql
CREATE DATABASE university_portal;
USE university_portal;

CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(16) NOT NULL UNIQUE,
    course_name VARCHAR(255) NOT NULL,
    instructor VARCHAR(255),
    schedule VARCHAR(255)
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE student_course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
    UNIQUE (student_id, course_id)
);

-- sample data
INSERT INTO courses (course_code, course_name, instructor, schedule) VALUES
    ('CS101', 'Intro to Computer Science', 'Dr. Alice', 'Mon 09:00-10:30'),
    ('MATH201', 'Calculus II', 'Dr. Bob', 'Tue 11:00-12:30');

INSERT INTO students (first_name, last_name, email) VALUES
    ('John', 'Doe', 'john.doe@example.com'),
    ('Jane', 'Smith', 'jane.smith@example.com');

INSERT INTO student_course (student_id, course_id) VALUES
    (1, 1), (1, 2), (2, 1);

CREATE TABLE faculty (
    faculty_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255)
);

ALTER TABLE courses
    ADD COLUMN faculty_id INT;

ALTER TABLE courses
    ADD CONSTRAINT fk_faculty
    FOREIGN KEY (faculty_id)
    REFERENCES faculty(faculty_id)
    ON DELETE SET NULL
    ON UPDATE CASCADE;
```
