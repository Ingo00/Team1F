package com.teamf.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.teamf.domain.User;

/**
 * Test fixture for the UserController using a mock UserRepository.
 */
public class UserControllerTest {

    private UserController controller;
    private MockUserRepository mockRepo;

    /**
     * Initializes the controller and mock repository before each test.
     */
    @BeforeEach
    void setUp() {
        mockRepo = new MockUserRepository();
        controller = new UserController(mockRepo);
    }

    /**
     * Tests that registering a new user succeeds.
     */
    @Test
    void testRegisterNewUser() {
        User user = new User("alice", "alice@example.com");
        boolean success = controller.registerUser(user);
        assertTrue(success);
        assertNotNull(mockRepo.findByUsername("alice"));
    }

    /**
     * Tests that registering a user with an existing username fails.
     */
    @Test
    void testRegisterExistingUserFails() {
        User user = new User("bob", "bob@example.com");
        mockRepo.save(user);
        boolean success = controller.registerUser(user);
        assertFalse(success);
    }

    /**
     * Tests that deleting an existing user works as expected.
     */
    @Test
    void testDeleteExistingUser() {
        User user = new User("carol", "carol@example.com");
        mockRepo.save(user);
        boolean success = controller.deleteUser("carol");
        assertTrue(success);
        assertNull(mockRepo.findByUsername("carol"));
    }

    /**
     * Tests that deleting a non-existent user fails.
     */
    @Test
    void testDeleteNonExistingUserFails() {
        boolean success = controller.deleteUser("dan");
        assertFalse(success);
    }
}
