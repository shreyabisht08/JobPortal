package com.jobportal.service;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection); // Initialize UserDAO with a connection
    }

    // Add a new user
    public boolean addUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Use hashing if needed
        user.setEmail(email);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return userDAO.addUser(user);
    }

    // Retrieve a user by ID
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    // Update a user's information
    public boolean updateUser(int userId, String username, String password, String email) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            return false; // User not found
        }

        user.setUsername(username);
        user.setPassword(password); // Use hashing if needed
        user.setEmail(email);

        return userDAO.updateUser(user);
    }

    // Delete a user
    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
