package com.jobportal.servlet;

import java.io.IOException;

import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ApplyServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.sendRedirect("jobs.jsp");
	    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        int jobId = Integer.parseInt(request.getParameter("job_id"));
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortalDB", "root", "Shreya@5")) {
            String query = "INSERT INTO Applications (job_id, user_id, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jobId);
            ps.setInt(2, userId);
            ps.setString(3, "Pending");
            
            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("applications.jsp");
            } else {
                request.setAttribute("error", "Failed to apply for the job");
                request.getRequestDispatcher("jobs.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
