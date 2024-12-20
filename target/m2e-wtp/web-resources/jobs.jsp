<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Listings</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Available Jobs</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Job Title</th>
                    <th>Company</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortalDB", "root", "Shreya@5");
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Jobs");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("job_title") %></td>
                    <td><%= rs.getString("company") %></td>
                    <td><%= rs.getString("location") %></td>
                    <td><%= rs.getString("description") %></td>
                    <td>
                        <form action="ApplyServlet" method="post">
                            <input type="hidden" name="job_id" value="<%= rs.getInt("job_id") %>">
                            <button type="submit" class="btn btn-success">Apply</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                    conn.close();
                %>
            </tbody>
        </table>
    </div>

</body>
</html>