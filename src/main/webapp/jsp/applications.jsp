<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Applications</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2 class="text-center">My Applications</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Job Title</th>
                    <th>Company</th>
                    <th>Status</th>
                    <th>Application Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String userId = (String) session.getAttribute("user_id");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortalDB", "root", "Shreya@5");
                    PreparedStatement ps = conn.prepareStatement(
                        "SELECT j.job_title, j.company, a.status, a.application_date " +
                        "FROM Applications a JOIN Jobs j ON a.job_id = j.job_id WHERE a.user_id = ?");
                    ps.setInt(1, Integer.parseInt(userId));
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("job_title") %></td>
                    <td><%= rs.getString("company") %></td>
                    <td><%= rs.getString("status") %></td>
                    <td><%= rs.getTimestamp("application_date") %></td>
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
