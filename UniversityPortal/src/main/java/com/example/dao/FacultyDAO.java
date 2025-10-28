// src/main/java/com/example/dao/FacultyDAO.java
package com.example.dao;

import com.example.model.Faculty;
import com.example.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {

    // --- CREATE: Add new faculty ---
    public boolean addFaculty(Faculty faculty) throws SQLException {
        String sql = "INSERT INTO faculty (name, department) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, faculty.getName());
            ps.setString(2, faculty.getDepartment());

            return ps.executeUpdate() > 0;
        }
    }

    // --- READ: Get all faculties ---
   public List<Faculty> getAllFaculties() throws SQLException {
    List<Faculty> list = new ArrayList<>();
    String sql = "SELECT faculty_id, name FROM faculty";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Faculty f = new Faculty();
            f.setFacultyId(rs.getInt("faculty_id"));
            f.setName(rs.getString("name"));
            list.add(f);
        }
    }
    return list; // never null
}


    // --- READ: Get faculty by ID ---
    public Faculty getFacultyById(int id) throws SQLException {
        String sql = "SELECT faculty_id, name, department FROM faculty WHERE faculty_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Faculty(
                            rs.getInt("faculty_id"),
                            rs.getString("name"),
                            rs.getString("department")
                    );
                }
            }
        }
        return null;
    }

    // --- UPDATE: Update faculty info ---
    public boolean updateFaculty(Faculty faculty) throws SQLException {
        String sql = "UPDATE faculty SET name = ?, department = ? WHERE faculty_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, faculty.getName());
            ps.setString(2, faculty.getDepartment());
            ps.setInt(3, faculty.getFacultyId());

            return ps.executeUpdate() > 0;
        }
    }

    // --- DELETE: Remove faculty ---
    public boolean deleteFaculty(int facultyId) throws SQLException {
        String sql = "DELETE FROM faculty WHERE faculty_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facultyId);
            return ps.executeUpdate() > 0;
        }
    }
}
