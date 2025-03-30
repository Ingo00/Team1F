package com.teamf.controller;

import com.teamf.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserController controller;
    private MockUserRepository mockRepo;

    @BeforeEach
    void setUp() {
        mockRepo = new MockUserRepository();
        controller = new UserController(mockRepo);
    }

    @Test
    void testRegisterNewUser() {
        User user = new User("alice", "alice@example.com");
        boolean success = controller.registerUser(user);
        assertTrue(success);
        assertNotNull(mockRepo.findByUsername("alice"));
    }

    @Test
    void testRegisterExistingUserFails() {
        User user = new User("bob", "bob@example.com");
        mockRepo.save(user);
        boolean success = controller.registerUser(user);
        assertFalse(success);
    }

    @Test
    void testDeleteExistingUser() {
        User user = new User("carol", "carol@example.com");
        mockRepo.save(user);
        boolean success = controller.deleteUser("carol");
        assertTrue(success);
        assertNull(mockRepo.findByUsername("carol"));
    }

    @Test
    void testDeleteNonExistingUserFails() {
        boolean success = controller.deleteUser("dan");
        assertFalse(success);
    }
}