package com.example.dao;

import com.example.model.Course;
import com.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

   // --- READ: Get all courses ---
public List<Course> getAllCourses() throws SQLException {
    String sql = "SELECT course_id, course_code, course_name, instructor, schedule, faculty_id FROM courses";
    List<Course> list = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

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
    return list;
}

// --- READ: Get course by ID ---
public Course getCourseById(int id) throws SQLException {
    String sql = "SELECT course_id, course_code, course_name, instructor, schedule, faculty_id FROM courses WHERE course_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("instructor"),
                        rs.getString("schedule"),
                        rs.getObject("faculty_id") != null ? rs.getInt("faculty_id") : null
                );
            }
        }
    }
    return null;
}


   // --- CREATE: Add new course ---
public boolean addCourse(Course course) throws SQLException {
    String sql = "INSERT INTO courses (course_code, course_name, instructor, schedule, faculty_id) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, course.getCourseCode());
        ps.setString(2, course.getCourseName());
        ps.setString(3, course.getInstructor());
        ps.setString(4, course.getSchedule());
        
        if (course.getFacultyId() != null) {
            ps.setInt(5, course.getFacultyId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }

        return ps.executeUpdate() > 0;
    }
}

// --- UPDATE: Update existing course ---
public boolean updateCourse(Course course) throws SQLException {
    String sql = "UPDATE courses SET course_code = ?, course_name = ?, instructor = ?, schedule = ?, faculty_id = ? WHERE course_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, course.getCourseCode());
        ps.setString(2, course.getCourseName());
        ps.setString(3, course.getInstructor());
        ps.setString(4, course.getSchedule());

        if (course.getFacultyId() != null) {
            ps.setInt(5, course.getFacultyId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }

        ps.setInt(6, course.getCourseId());

        return ps.executeUpdate() > 0;
    }
}

    // --- DELETE: Remove course ---
    public boolean deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            return ps.executeUpdate() > 0;
        }
    }
}
