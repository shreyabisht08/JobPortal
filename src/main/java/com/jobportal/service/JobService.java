package com.jobportal.service;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;

import java.sql.Connection;
import java.util.List;

public class JobService {
    private JobDAO jobDAO;

    public JobService(Connection connection) {
        this.jobDAO = new JobDAO(connection);
    }

    // Add a new job
    public boolean addJob(String jobTitle, String company, String location, String description) {
        Job job = new Job();
        job.setJobTitle(jobTitle);
        job.setCompany(company);
        job.setLocation(location);
        job.setDescription(description);
        job.setPostedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        return jobDAO.addJob(job);
    }

    // Retrieve a job by ID
    public Job getJobById(int jobId) {
        return jobDAO.getJobById(jobId);
    }

    // Retrieve all jobs
    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    // Update an existing job
    public boolean updateJob(int jobId, String jobTitle, String company, String location, String description) {
        Job job = jobDAO.getJobById(jobId);
        if (job != null) {
            job.setJobTitle(jobTitle);
            job.setCompany(company);
            job.setLocation(location);
            job.setDescription(description);
            return jobDAO.updateJob(job);
        }
        return false;
    }

    // Delete a job by ID
    public boolean deleteJob(int jobId) {
        return jobDAO.deleteJob(jobId);
    }
}
