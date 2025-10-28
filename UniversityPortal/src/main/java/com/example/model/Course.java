package com.example.model;

public class Course {

    private int courseId;
    private String courseCode;
    private String courseName;
    private String instructor;
    private String schedule;
    private Integer facultyId; // nullable, links to Faculty

    // --- No-argument constructor ---
    public Course() {
    }

    // --- Constructor without ID (for creating new courses) ---
    public Course(String courseCode, String courseName, String instructor, String schedule, Integer facultyId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.schedule = schedule;
        this.facultyId = facultyId;
    }

    // --- Full constructor ---
    public Course(int courseId, String courseCode, String courseName, String instructor, String schedule, Integer facultyId) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.schedule = schedule;
        this.facultyId = facultyId;
    }

    // --- Getters ---
    public int getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getSchedule() {
        return schedule;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    // --- Setters ---
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", instructor='" + instructor + '\'' +
                ", schedule='" + schedule + '\'' +
                ", facultyId=" + facultyId +
                '}';
    }
}
