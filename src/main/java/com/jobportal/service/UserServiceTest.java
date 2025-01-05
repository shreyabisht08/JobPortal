package com.jobportal.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testAddUser() {
        User user = new User(1L, "John Doe", "john.doe@example.com");

        userService.addUser(user);

        List<User> users = userService.getAllUsers();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("John Doe", users.get(0).getName());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1L, "Alice", "alice@example.com");
        User user2 = new User(2L, "Bob", "bob@example.com");

        userService.addUser(user1);
        userService.addUser(user2);

        List<User> users = userService.getAllUsers();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void testGetUserById() {
        User user = new User(1L, "John Doe", "john.doe@example.com");

        userService.addUser(user);

        User fetchedUser = userService.getUserById(1L);
        Assertions.assertNotNull(fetchedUser);
        Assertions.assertEquals("John Doe", fetchedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User(1L, "John Doe", "john.doe@example.com");

        userService.addUser(user);
        userService.deleteUser(1L);

        User deletedUser = userService.getUserById(1L);
        Assertions.assertNull(deletedUser);
    }

    // Inner class simulating UserService for test purposes
    class UserService {

        private List<User> users = new ArrayList<>();

        public void addUser(User user) {
            users.add(user);
        }

        public List<User> getAllUsers() {
            return new ArrayList<>(users);
        }

        public User getUserById(Long id) {
            return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        }

        public void deleteUser(Long id) {
            users.removeIf(user -> user.getId().equals(id));
        }
    }

    // Inner class simulating User model for test purposes
    class User {
        private Long id;
        private String name;
        private String email;

        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}