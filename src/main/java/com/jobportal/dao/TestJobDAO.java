package com.jobportal.dao;

import com.jobportal.model.Job;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TestJobDAO {
    public static void main(String[] args) {
        // Database connection setup
        String jdbcUrl = "jdbc:mysql://localhost:3306/JobPortalDB";
        String username = "Shreya@5";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Database connection established.");

            JobDAO jobDAO = new JobDAO(connection);

            // 1. Add a new job
            Job newJob = new Job();
            newJob.setJobTitle("Software Engineer");
            newJob.setCompany("TechCorp");
            newJob.setLocation("New York");
            newJob.setDescription("Responsible for developing Java applications.");
            newJob.setPostedAt(new Timestamp(System.currentTimeMillis()));

            boolean isAdded = jobDAO.addJob(newJob);
            System.out.println("Job added: " + isAdded);

            // 2. Retrieve the job by ID (assuming job ID 1 for testing)
            int jobIdToRetrieve = 1; // Change this ID to match an existing one in your table
            Job retrievedJob = jobDAO.getJobById(jobIdToRetrieve);
            if (retrievedJob != null) {
                System.out.println("Retrieved Job: " + retrievedJob.getJobTitle());
            } else {
                System.out.println("Job not found with ID: " + jobIdToRetrieve);
            }

            // 3. Update the job
            if (retrievedJob != null) {
                retrievedJob.setJobTitle("Senior Software Engineer");
                boolean isUpdated = jobDAO.updateJob(retrievedJob);
                System.out.println("Job updated: " + isUpdated);
            }

            // 4. Get all jobs
            List<Job> allJobs = jobDAO.getAllJobs();
            System.out.println("All Jobs:");
            for (Job job : allJobs) {
                System.out.println("Job ID: " + job.getJobId() + ", Title: " + job.getJobTitle());
            }

            // 5. Delete the job by ID
            int jobIdToDelete = 1; // Change this ID to match an existing one in your table
            boolean isDeleted = jobDAO.deleteJob(jobIdToDelete);
            System.out.println("Job deleted: " + isDeleted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




