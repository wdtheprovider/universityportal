<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.example.model.Course, com.example.model.Faculty" %>

        <% List<Course> courses = (List<Course>) request.getAttribute("courses");
                List<Faculty> faculties = (List<Faculty>) request.getAttribute("faculties");
                        %>

                        <!DOCTYPE html>
                        <html lang="en">

                        <head>
                            <meta charset="UTF-8">
                            <title>All Courses - CRUD</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    margin: 40px;
                                    background-color: #f9f9f9;
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
                                    vertical-align: top;
                                }

                                th {
                                    background-color: #007BFF;
                                    color: white;
                                }

                                tr:nth-child(even) {
                                    background-color: #f2f2f2;
                                }

                                input[type="text"],
                                select {
                                    width: 95%;
                                    padding: 5px;
                                    margin: 2px 0;
                                    border: 1px solid #ccc;
                                    border-radius: 4px;
                                }

                                button {
                                    padding: 6px 12px;
                                    margin-top: 5px;
                                    border: none;
                                    border-radius: 4px;
                                    cursor: pointer;
                                }

                                .btn-save {
                                    background-color: #28a745;
                                    color: white;
                                }

                                .btn-save:hover {
                                    background-color: #218838;
                                }

                                .btn-delete {
                                    background-color: #dc3545;
                                    color: white;
                                }

                                .btn-delete:hover {
                                    background-color: #c82333;
                                }

                                .create-form {
                                    background-color: #fff;
                                    padding: 20px;
                                    margin-bottom: 20px;
                                    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
                                    border-radius: 8px;
                                }

                                a {
                                    color: #007BFF;
                                    text-decoration: none;
                                    margin-right: 15px;
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

                            <h1>All Courses</h1>

                            <!-- Create Course Form -->
                            <div class="create-form">
                                <h3>Add New Course</h3>
                                <form method="post" action="<%=request.getContextPath()%>/courses/create">
                                    Code: <input type="text" name="courseCode" required /><br />
                                    Name: <input type="text" name="courseName" required /><br />
                                    Instructor: <input type="text" name="instructor" /><br />
                                    Schedule: <input type="text" name="schedule" /><br />
                                    Faculty:
                                    <select name="facultyId" required>
                                        <option value="">-- Select Faculty --</option>
                                        <% for(Faculty f : faculties) { %>
                                            <option value="<%= f.getFacultyId() %>">
                                                <%= f.getName() %>
                                            </option>
                                            <% } %>
                                    </select>
                                    <br />
                                    <button type="submit" class="btn-save">Add Course</button>
                                </form>
                            </div>

                            <!-- Courses Table -->
                            <table>
                                <tr>
                                    <th>Code</th>
                                    <th>Name</th>
                                    <th>Instructor</th>
                                    <th>Schedule</th>
                                    <th>Faculty</th>
                                    <th>Actions</th>
                                </tr>

                                <% if (courses !=null && !courses.isEmpty()) { for (Course c : courses) { %>
                                    <tr>
                                        <form method="post" action="<%=request.getContextPath()%>/courses/update">
                                            <td>
                                                <input type="hidden" name="courseId" value="<%= c.getCourseId() %>" />
                                                <input type="text" name="courseCode" value="<%= c.getCourseCode() %>" />
                                            </td>
                                            <td>
                                                <input type="text" name="courseName" value="<%= c.getCourseName() %>" />
                                            </td>
                                            <td>
                                                <input type="text" name="instructor" value="<%= c.getInstructor() %>" />
                                            </td>
                                            <td>
                                                <input type="text" name="schedule" value="<%= c.getSchedule() %>" />
                                            </td>
                                            <td>
                                                <select name="facultyId" required>
                                                    <option value="">-- Select Faculty --</option>
                                                    <% for(Faculty f : faculties) { %>
                                                        <option value="<%= f.getFacultyId() %>" <%=(c.getFacultyId()
                                                            !=null && c.getFacultyId().equals(f.getFacultyId()))
                                                            ? "selected" : "" %>>
                                                            <%= f.getName() %>
                                                        </option>
                                                        <% } %>
                                                </select>
                                            </td>
                                            <td>
                                                <button type="submit" class="btn-save">Save</button>
                                                <a href="<%=request.getContextPath()%>/courses/delete?courseId=<%=c.getCourseId()%>"
                                                    class="btn-delete"
                                                    style="text-decoration:none;padding:6px 12px;">Delete</a>
                                            </td>
                                        </form>
                                    </tr>
                                    <% } } else { %>
                                        <tr>
                                            <td colspan="6" class="no-courses">No courses available.</td>
                                        </tr>
                                        <% } %>
                            </table>

                            <p>
                                <a href="<%=request.getContextPath()%>/student">Student view</a>
                                <a href="<%=request.getContextPath()%>/faculty">Faculty view</a>
                            </p>

                        </body>

                        </html>