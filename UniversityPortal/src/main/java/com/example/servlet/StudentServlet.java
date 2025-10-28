// src/main/java/com/example/servlet/StudentServlet.java
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

@WebServlet("/student")  // This stays as "/student"
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Redirect to form if accessed via GET
        resp.sendRedirect(req.getContextPath() + "/student-form");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("studentId");
        if (idStr == null || idStr.trim().isEmpty()) {
            req.setAttribute("error", "Student ID is required.");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_form.jsp").forward(req, resp);
            return;
        }

        try {
            int studentId = Integer.parseInt(idStr.trim());
            Student s = studentDAO.getStudentById(studentId);
            if (s == null) {
                req.setAttribute("error", "No student found with ID: " + studentId);
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_form.jsp").forward(req, resp);
                return;
            }
            List<Course> courses = studentDAO.getCoursesForStudent(studentId);
            req.setAttribute("student", s);
            req.setAttribute("courses", courses);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_schedule.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid student ID format.");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_form.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}