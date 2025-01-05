<%@ page import="java.sql.*" %>
<%
    String username = request.getParameter("username");
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/JobPortalDB", "root", "your_password");

        String sql = "SELECT * FROM users WHERE username = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();

        if (rs.next()) {
%>
            <h2>Welcome, <%= rs.getString("username") %>!</h2>
            <p>Email: <%= rs.getString("email") %></p>
<%
        } else {
            out.println("No user found with username: " + username);
        }
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
    }
%>
