CREATE DATABASE JobPortalDB;
SHOW DATABASES;
USE JobPortalDB;
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DESCRIBE Users;
CREATE TABLE Jobs (
    job_id INT AUTO_INCREMENT PRIMARY KEY,
    job_title VARCHAR(100) NOT NULL,
    company VARCHAR(100),
    location VARCHAR(50),
    description TEXT,
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DESCRIBE Jobs;
CREATE TABLE Applications (
    app_id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT,
    user_id INT,
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (job_id) REFERENCES Jobs(job_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);
DESCRIBE Applications;
SHOW CREATE TABLE Applications;
INSERT INTO Users (username, password, email) VALUES ('john_doe', 'hashed_password', 'john@example.com');
INSERT INTO Users (username, password, email) VALUES ('jane_doe', 'hashed_password', 'jane@example.com');
INSERT INTO Jobs (job_title, company, location, description) VALUES ('Software Engineer', 'Tech Corp', 'New York', 'Develop and maintain software solutions.');
INSERT INTO Jobs (job_title, company, location, description) VALUES ('Data Analyst', 'Data Insights', 'San Francisco', 'Analyze data to support business decisions.');
INSERT INTO Applications (job_id, user_id, status) VALUES (1, 1, 'Pending');
INSERT INTO Applications (job_id, user_id, status) VALUES (2, 2, 'Pending');
SELECT * FROM Users;
SELECT * FROM Jobs;
SELECT 
    *
FROM
    Applications;
    
