package com.example.servlet;

import com.example.dao.FacultyDAO;
import com.example.model.Faculty;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    private FacultyDAO facultyDAO = new FacultyDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Faculty> faculties = facultyDAO.getAllFaculties();
            req.setAttribute("faculties", faculties);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/faculty_list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); // "create", "update", "delete"

        try {
            if ("create".equals(action)) {
                handleCreate(req, resp);
            } else if ("update".equals(action)) {
                handleUpdate(req, resp);
            } else if ("delete".equals(action)) {
                handleDelete(req, resp);
            } else {
                req.setAttribute("error", "Unknown action.");
                doGet(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String name = req.getParameter("facultyName");

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Faculty name is required.");
            doGet(req, resp);
            return;
        }

        Faculty faculty = new Faculty();
        faculty.setName(name.trim());

        boolean ok = facultyDAO.addFaculty(faculty);
        req.setAttribute("message", ok ? "Faculty created successfully." : "Failed to create faculty.");
        doGet(req, resp);
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String idStr = req.getParameter("facultyId");
        String name = req.getParameter("facultyName");

        if (idStr == null || idStr.isEmpty() || name == null || name.trim().isEmpty()) {
            req.setAttribute("error", "Faculty ID and name are required.");
            doGet(req, resp);
            return;
        }

        int id = Integer.parseInt(idStr);
        Faculty faculty = new Faculty();
        faculty.setFacultyId(id);
        faculty.setName(name.trim());

        boolean ok = facultyDAO.updateFaculty(faculty);
        req.setAttribute("message", ok ? "Faculty updated successfully." : "Update failed.");
        doGet(req, resp);
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String idStr = req.getParameter("facultyId");

        if (idStr == null || idStr.isEmpty()) {
            req.setAttribute("error", "Faculty ID is required for deletion.");
            doGet(req, resp);
            return;
        }

        int id = Integer.parseInt(idStr);
        boolean ok = facultyDAO.deleteFaculty(id);
        req.setAttribute("message", ok ? "Faculty deleted successfully." : "Deletion failed.");
        doGet(req, resp);
    }
}
