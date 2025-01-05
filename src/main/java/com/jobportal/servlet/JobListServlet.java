package com.jobportal.servlet;

import java.io.IOException;

import com.jobportal.model.Job;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/JobListServlet")
public class JobListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortalDB", "root", "Shreya@5");

            // Query to fetch jobs
            String sql = "SELECT job_id, job_title, company, location, description FROM Jobs";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Store job details in a list
            List<Job> jobList = new ArrayList<>();
            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setDescription(rs.getString("description"));
                jobList.add(job);
            }

            conn.close();

            // Pass the job list to JSP
            request.setAttribute("jobs", jobList);
        } catch (Exception e) {
            request.setAttribute("error", "Error fetching jobs: " + e.getMessage());
        }

       
		// Forward to JSP
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("jobs.jsp");
        dispatcher.forward(request, response);
    }
}
