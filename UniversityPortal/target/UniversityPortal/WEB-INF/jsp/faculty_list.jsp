<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List, com.example.model.Faculty" %>

        <% List<Faculty> faculties = (List<Faculty>) request.getAttribute("faculties");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <title>Manage Faculties</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f9f9f9;
                            margin: 40px;
                        }

                        h1,
                        h2 {
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

                        input[type="text"] {
                            width: 95%;
                            padding: 5px;
                            margin: 2px 0;
                            border: 1px solid #ccc;
                            border-radius: 4px;
                            box-sizing: border-box;
                        }

                        button {
                            padding: 6px 12px;
                            background-color: #28a745;
                            color: white;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                            margin-top: 5px;
                        }

                        button:hover {
                            background-color: #218838;
                        }

                        .message {
                            margin-top: 15px;
                            font-weight: bold;
                        }

                        .error {
                            color: red;
                        }

                        .success {
                            color: green;
                        }

                        a {
                            color: #007BFF;
                            text-decoration: none;
                        }

                        a:hover {
                            text-decoration: underline;
                        }

                        form {
                            margin: 0;
                        }
                    </style>
                </head>

                <body>

                    <h1>Manage Faculties</h1>

                    <c:if test="${not empty error}">
                        <p class="message error">${error}</p>
                    </c:if>
                    <c:if test="${not empty message}">
                        <p class="message success">${message}</p>
                    </c:if>

                    <!-- ADD NEW FACULTY -->
                    <h2>Add New Faculty</h2>
                    <form method="post" action="${pageContext.request.contextPath}/faculty">
                        <input type="hidden" name="action" value="create" />
                        Name: <input type="text" name="facultyName" required /><br />
                        <button type="submit">Add Faculty</button>
                    </form>

                    <hr />

                    <!-- EXISTING FACULTIES -->
                    <table>
                        <tr>
                            <th>Faculty ID</th>
                            <th>Name</th>
                            <th>Actions</th>
                        </tr>

                        <% if (faculties !=null && !faculties.isEmpty()) { for (Faculty f : faculties) { %>
                            <tr>
                                <form method="post" action="${pageContext.request.contextPath}/faculty">
                                    <input type="hidden" name="action" value="update" />
                                    <td>
                                        <input type="hidden" name="facultyId" value="<%= f.getFacultyId() %>" />
                                        <%= f.getFacultyId() %>
                                    </td>
                                    <td>
                                        <input type="text" name="facultyName" value="<%= f.getName() %>" required />
                                    </td>
                                    <td>
                                        <button type="submit">Save</button>
                                        <a href="${pageContext.request.contextPath}/faculty?action=delete&facultyId=<%= f.getFacultyId() %>"
                                            onclick="return confirm('Are you sure you want to delete this faculty?')">Delete</a>
                                    </td>
                                </form>
                            </tr>
                            <% } } else { %>
                                <tr>
                                    <td colspan="3" style="text-align:center; font-style:italic;">No faculties found
                                    </td>
                                </tr>
                                <% } %>
                    </table>

                    <p><a href="<%= request.getContextPath() %>">Back to Dashboard</a></p>

                </body>

                </html>