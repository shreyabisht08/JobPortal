package com.jobportal.servlet;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
@WebServlet("/ApplicationsServlet")
public class ApplicationsServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortalDB", "root", "Shreya@5")) {
            String query = "SELECT j.job_title, j.company, a.status, a.application_date " +
                           "FROM Applications a JOIN Jobs j ON a.job_id = j.job_id WHERE a.user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            ArrayList<String[]> applications = new ArrayList<>();
            while (rs.next()) {
                applications.add(new String[]{
                    rs.getString("job_title"),
                    rs.getString("company"),
                    rs.getString("status"),
                    rs.getTimestamp("application_date").toString()
                });
            }

            request.setAttribute("applications", applications);
            request.getRequestDispatcher("applications.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
