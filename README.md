JobPortal Project Documentation
1. Project Overview
1.1 Project Name
JobPortal

1.2 Description
The JobPortal project is a web-based application designed to facilitate job searching and recruitment. The system allows job seekers to search for and apply to job listings, while employers can post jobs and manage applications.

2. Prerequisites
2.1 System Requirements
Java Development Kit (JDK): Version 11 or higher
Apache Maven: Version 3.8.0 or higher
Apache Tomcat: Version 9.0 or higher

2.2 Software Requirements
Database: MySQL (version 8.0 or higher)
Web Browser: Google Chrome, Firefox, or any modern browser

4. Installation Instructions

3.1 Clone the Repository
git clone https://github.com/shreyabisht08/jobportal.git

3.2 Configure the Database
Create a MySQL database named jobportal.
Import the SQL schema from src/main/resources/schema.sql.
3.3 Update Application Properties
Update the database configurations in src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=Shreya@5

3.4 Build the Project
Run the following Maven command to build the project:
mvn clean install

3.5 Deploy to Tomcat
Copy the generated WAR file from target/jobportal.war to the webapps folder of your Tomcat server.
Start the Tomcat server:
catalina.bat run (Windows)
./catalina.sh run (Linux/Mac)
4. Project Structure
JobPortal/
├── src/
│   ├── main/
│   │   ├── java/ (Source code)
│   │   ├── resources/ (Configuration files)
│   │   └── webapp/ (Frontend)
│   ├── test/ (Test cases)
├── pom.xml (Maven dependencies and build configurations)
└── README.md (Project overview)

5. Usage
5.1 Access the Application
Open a browser and navigate to http://localhost:8080/jobportal.
Login as:
Admin: Manage job postings, users, and applications.
Job Seeker: Search for and apply to jobs.
Employer: Post jobs and manage applications.

5.2 API Endpoints
Method	Endpoint	Description
GET	/api/jobs	Fetch all job listings
POST	/api/jobs	Add a new job
GET	/api/users	Fetch all users
POST	/api/applications	Submit a job application

6. Testing
6.1 Run Unit Tests
Run the following command:
mvn test

7. Dependencies
MySQL Connector: Database driver
JUnit 5: Unit testing framework

9. Contribution Guidelines
Fork the repository and create a new branch for your feature.
Submit a pull request with a clear description of your changes.

11. Future Enhancements
Implement real-time job notifications.
Add advanced search filters for job seekers.
Introduce a recommendation system for job suggestions.

13. Contact
For any issues or questions, contact:
Name: Shreya Bisht
Email: shreyabisht0801gmail.com

