// src/main/java/com/example/dao/StudentDAO.java
package com.example.dao;

import com.example.model.Course;
import com.example.model.Student;
import com.example.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // --- READ: Get student by ID ---
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT student_id, first_name, last_name, email FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    // --- READ: Get all students ---
    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT student_id, first_name, last_name, email FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                ));
            }
        }
        return students;
    }

    // --- CREATE: Add a new student ---
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.executeUpdate();
        }
    }

    // --- UPDATE: Update student details ---
    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getStudentId());
            ps.executeUpdate();
        }
    }

    // --- DELETE: Remove a student ---
    public void deleteStudent(int studentId) throws SQLException {
        // First remove student enrollments
        String deleteEnrollments = "DELETE FROM student_course WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteEnrollments)) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }

        // Then remove the student
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }
    }

   // --- READ: Get courses for a student ---
public List<Course> getCoursesForStudent(int studentId) throws SQLException {
    String sql = "SELECT c.course_id, c.course_code, c.course_name, c.instructor, c.schedule, c.faculty_id " +
                 "FROM courses c " +
                 "JOIN student_course sc ON c.course_id = sc.course_id " +
                 "WHERE sc.student_id = ?";
    List<Course> list = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, studentId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("instructor"),
                        rs.getString("schedule"),
                        rs.getObject("faculty_id") != null ? rs.getInt("faculty_id") : null
                ));
            }
        }
    }
    return list;
}


    // --- CREATE/UPDATE: Enroll a student in a course ---
    public void enrollStudentInCourse(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO student_course (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    // --- DELETE: Remove a student from a course ---
    public void removeStudentFromCourse(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM student_course WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }
}
