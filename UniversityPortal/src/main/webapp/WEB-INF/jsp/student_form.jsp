<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Student Schedule</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 40px;
            }

            h1 {
                color: #333;
            }

            form {
                margin-top: 20px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
                max-width: 400px;
            }

            label {
                display: block;
                margin-bottom: 15px;
                font-weight: bold;
            }

            input[type="text"] {
                width: 100%;
                padding: 8px 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            button {
                padding: 10px 20px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 14px;
            }

            button:hover {
                background-color: #0056b3;
            }

            .error {
                color: red;
                margin-top: 15px;
                font-style: italic;
            }
        </style>
    </head>

    <body>

        <h1>View Student Schedule</h1>

        <form method="post" action="${pageContext.request.contextPath}/student">
            <label>
                Student ID:
                <input type="text" name="studentId" />
            </label>
            <button type="submit">View Schedule</button>
        </form>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

    </body>

    </html>