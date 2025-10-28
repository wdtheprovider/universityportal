// src/main/java/com/example/servlet/StudentFormServlet.java
package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/student-form")
public class StudentFormServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Just display the form
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/student_form.jsp").forward(req, resp);
    }
}