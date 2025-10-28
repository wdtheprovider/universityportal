<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.example.model.Course, com.example.model.Student" %>

        <% Student student=(Student) request.getAttribute("student"); List<Course> courses = (List<Course>)
                request.getAttribute("courses");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <title>Schedule for <%= student.getFirstName() %>
                    </title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f9f9f9;
                            margin: 40px;
                        }

                        h1 {
                            color: #333;
                        }

                        table {
                            width: 100%;
                            border-collapse: collapse;
                            margin-top: 20px;
                            background-color: #fff;
                            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
                        }

                        th,
                        td {
                            padding: 12px 15px;
                            border: 1px solid #ddd;
                            text-align: left;
                        }

                        th {
                            background-color: #007BFF;
                            color: white;
                        }

                        tr:nth-child(even) {
                            background-color: #f2f2f2;
                        }

                        a {
                            color: #007BFF;
                            text-decoration: none;
                        }

                        a:hover {
                            text-decoration: underline;
                        }

                        .no-courses {
                            text-align: center;
                            font-style: italic;
                            color: #666;
                        }
                    </style>
                </head>

                <body>

                    <h1>
                        Schedule for <%= student.getFirstName() %>
                            <%= student.getLastName() %>
                                (ID: <%= student.getStudentId() %>)
                    </h1>

                    <table>
                        <tr>
                            <th>Course Code</th>
                            <th>Course Name</th>
                            <th>Instructor</th>
                            <th>Schedule</th>
                        </tr>

                        <% if (courses !=null && !courses.isEmpty()) { for (Course c : courses) { %>
                            <tr>
                                <td>
                                    <%= c.getCourseCode() %>
                                </td>
                                <td>
                                    <%= c.getCourseName() %>
                                </td>
                                <td>
                                    <%= c.getInstructor() %>
                                </td>
                                <td>
                                    <%= c.getSchedule() %>
                                </td>
                            </tr>
                            <% } } else { %>
                                <tr>
                                    <td colspan="4" class="no-courses">No courses enrolled.</td>
                                </tr>
                                <% } %>
                    </table>

                    <p><a href="<%= request.getContextPath() %>/courses">Back to all courses</a></p>

                </body>

                </html>