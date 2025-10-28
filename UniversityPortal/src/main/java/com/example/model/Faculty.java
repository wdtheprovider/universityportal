// src/main/java/com/example/model/Faculty.java
package com.example.model;

public class Faculty {

    private int facultyId;
    private String name;
    private String department;

    // --- No-argument constructor ---
    public Faculty() {
    }

    // --- Constructor without ID (for creating new faculties) ---
    public Faculty(String name, String department) {
        this.name = name;
        this.department = department;
    }

    // --- Full constructor ---
    public Faculty(int facultyId, String name, String department) {
        this.facultyId = facultyId;
        this.name = name;
        this.department = department;
    }

    // --- Getters ---
    public int getFacultyId() {
        return facultyId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // --- Setters ---
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
