// src/main/java/com/example/servlet/StudentScheduleServlet.java
package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Course;
import com.example.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student-schedule")
public class StudentScheduleServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int studentId = 1; // Example: hardcoded student ID
            
            // Fetch the student object
            Student student = studentDAO.getStudentById(studentId);
            
            // Fetch the student's courses
            List<Course> courses = studentDAO.getCoursesForStudent(studentId);
            
            // Set BOTH attributes
            req.setAttribute("student", student);
            req.setAttribute("courses", courses);
            
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_schedule.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load student courses", e);
        }
    }
}