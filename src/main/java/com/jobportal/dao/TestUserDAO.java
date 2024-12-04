package com.jobportal.dao;

import com.jobportal.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.List;

public class TestUserDAO {
    public static void main(String[] args) {
        // Database connection setup
        String jdbcURL = "jdbc:mysql://localhost:3306/JobPortalDB";
        String jdbcUsername = "root";
        String jdbcPassword = "Shreya@5";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            System.out.println("Database connection established.");

            UserDAO userDAO = new UserDAO(connection);

            // 1. Test Add User
            User newUser = new User();
            newUser.setUsername("testuser");
            newUser.setPassword("password123");
            newUser.setEmail("testuser@example.com");
            newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            
            boolean isAdded = userDAO.addUser(newUser);
            System.out.println("User added: " + isAdded);

            // 2. Test Get User By ID
            User retrievedUser = userDAO.getUserById(1); // Assuming 1 is a valid user ID for testing
            if (retrievedUser != null) {
                System.out.println("User retrieved: " + retrievedUser.getUsername());
            } else {
                System.out.println("User not found with ID: 1");
            }

            // 3. Test Update User
            if (retrievedUser != null) {
                retrievedUser.setUsername("updateduser");
                retrievedUser.setPassword("newpassword");
                boolean isUpdated = userDAO.updateUser(retrievedUser);
                System.out.println("User updated: " + isUpdated);
            }

            // 4. Test Get All Users
            List<User> users = userDAO.getAllUsers();
            System.out.println("All users:");
            for (User user : users) {
                System.out.println(" - " + user.getUsername() + " (ID: " + user.getUserId() + ")");
            }

            // 5. Test Delete User
            if (retrievedUser != null) {
                boolean isDeleted = userDAO.deleteUser(retrievedUser.getUserId());
                System.out.println("User deleted: " + isDeleted);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





