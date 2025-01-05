package com.jobportal.service;

import com.jobportal.model.Job;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class JobServiceTest {
    public static void main(String[] args) {
        // Database connection setup
        String jdbcUrl = "jdbc:mysql://localhost:3306/JobPortalDB";
        String username = "root";
        String password = "Shreya@5";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Database connection established.");

            JobService jobService = new JobService(connection);

            // 1. Test Add Job
            boolean isAdded = jobService.addJob(
                    "Software Developer",
                    "InnovateCorp",
                    "San Francisco",
                    "Develop and maintain software solutions."
            );
            System.out.println("Job added via service: " + isAdded);

            // 2. Test Get Job By ID
            int jobIdToRetrieve = 1; // Change to a valid job ID in your database
            Job retrievedJob = jobService.getJobById(jobIdToRetrieve);
            if (retrievedJob != null) {
                System.out.println("Retrieved Job via service: " + retrievedJob.getJobTitle());
            } else {
                System.out.println("Job not found with ID: " + jobIdToRetrieve);
            }

            // 3. Test Update Job
            if (retrievedJob != null) {
                boolean isUpdated = jobService.updateJob(
                        retrievedJob.getJobId(),
                        "Senior Software Developer",
                        "InnovateCorp",
                        "San Francisco",
                        "Lead development teams and deliver software solutions."
                );
                System.out.println("Job updated via service: " + isUpdated);
            }

            // 4. Test Get All Jobs
            List<Job> allJobs = jobService.getAllJobs();
            System.out.println("All Jobs retrieved via service:");
            for (Job job : allJobs) {
                System.out.println(" - " + job.getJobTitle() + " (ID: " + job.getJobId() + ")");
            }

            // 5. Test Delete Job
            int jobIdToDelete = 1; // Change to a valid job ID in your database
            boolean isDeleted = jobService.deleteJob(jobIdToDelete);
            System.out.println("Job deleted via service: " + isDeleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
