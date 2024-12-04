package com.jobportal.dao;

import com.jobportal.model.Job;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    private Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new job
    public boolean addJob(Job job) {
        String sql = "INSERT INTO Jobs (job_title, company, location, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, job.getJobTitle());
            stmt.setString(2, job.getCompany());
            stmt.setString(3, job.getLocation());
            stmt.setString(4, job.getDescription());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a job by ID
    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM Jobs WHERE job_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setDescription(rs.getString("description"));
                job.setPostedAt(rs.getTimestamp("posted_at"));
                return job;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all jobs
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM Jobs";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setCompany(rs.getString("company"));
                job.setLocation(rs.getString("location"));
                job.setDescription(rs.getString("description"));
                job.setPostedAt(rs.getTimestamp("posted_at"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    // Update an existing job
    public boolean updateJob(Job job) {
        String sql = "UPDATE Jobs SET job_title = ?, company = ?, location = ?, description = ? WHERE job_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, job.getJobTitle());
            stmt.setString(2, job.getCompany());
            stmt.setString(3, job.getLocation());
            stmt.setString(4, job.getDescription());
            stmt.setInt(5, job.getJobId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a job by ID
    public boolean deleteJob(int jobId) {
        String sql = "DELETE FROM Jobs WHERE job_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jobId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}



