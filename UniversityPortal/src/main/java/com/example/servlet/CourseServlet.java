package com.example.servlet;

import com.example.dao.CourseDAO;
import com.example.dao.FacultyDAO;
import com.example.model.Course;
import com.example.model.Faculty;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/courses/*") // handles /courses, /courses/create, /courses/update, /courses/delete
public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getPathInfo(); // /create, /update, /delete, or null
    try {
        if (path == null || "/".equals(path)) {
            // Show all courses
            List<Course> courses = courseDAO.getAllCourses();

            // Fetch faculties
            FacultyDAO facultyDAO = new FacultyDAO();
            List<Faculty> faculties = facultyDAO.getAllFaculties();

            // Set attributes for JSP
            req.setAttribute("courses", courses);
            req.setAttribute("faculties", faculties);

            getServletContext().getRequestDispatcher("/WEB-INF/jsp/courses.jsp").forward(req, resp);
        } else if ("/delete".equals(path)) {
            deleteCourse(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    } catch (SQLException e) {
        throw new ServletException(e);
    }
}


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo(); // /create or /update
        try {
            if ("/create".equals(path)) {
                createCourse(req, resp);
            } else if ("/update".equals(path)) {
                updateCourse(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createCourse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String code = req.getParameter("courseCode");
        String name = req.getParameter("courseName");
        String instructor = req.getParameter("instructor");
        String schedule = req.getParameter("schedule");

        Course c = new Course();
        c.setCourseCode(code);
        c.setCourseName(name);
        c.setInstructor(instructor);
        c.setSchedule(schedule);

        courseDAO.addCourse(c);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

   private void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
    int id = Integer.parseInt(req.getParameter("courseId"));
    String code = req.getParameter("courseCode");
    String name = req.getParameter("courseName");
    String instructor = req.getParameter("instructor");
    String schedule = req.getParameter("schedule");
    String facultyIdStr = req.getParameter("facultyId"); // get facultyId from dropdown

    Integer facultyId = null;
    if (facultyIdStr != null && !facultyIdStr.isEmpty()) {
        facultyId = Integer.parseInt(facultyIdStr);
    }

    Course c = new Course(id, code, name, instructor, schedule, facultyId);
    courseDAO.updateCourse(c);

    resp.sendRedirect(req.getContextPath() + "/courses");
}


    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("courseId"));
        courseDAO.deleteCourse(id);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }
}
