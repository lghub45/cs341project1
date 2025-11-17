package tester;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.User;

public class UserTester {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "securePassword123");
    }

    @Test
    void testConstructorInitialization() {
        assertEquals("testUser", user.getUsername(), "Username should be initialized correctly");
        assertEquals("securePassword123", user.getPassword(), "Password should be initialized correctly");
    }

    @Test
    void testGetUsername() {
        assertEquals("testUser", user.getUsername(), "getUsername() should return the correct username");
    }

    @Test
    void testGetPassword() {
        assertEquals("securePassword123", user.getPassword(), "getPassword() should return the correct password");
    }

    @Test
    void testEmptyFields() {
        User emptyUser = new User("", "");
        assertEquals("", emptyUser.getUsername(), "Username should allow empty string");
        assertEquals("", emptyUser.getPassword(), "Password should allow empty string");
    }

    @Test
    void testNullFields() {
        User nullUser = new User(null, null);
        assertNull(nullUser.getUsername(), "Username should be null when passed as null");
        assertNull(nullUser.getPassword(), "Password should be null when passed as null");
    }

    @Test
    void testMultipleUsersIndependence() {
        User userA = new User("Alice", "Pass123");
        User userB = new User("Bob", "DiffPass456");

        assertNotEquals(userA.getUsername(), userB.getUsername(), "Each user should have its own username");
        assertNotEquals(userA.getPassword(), userB.getPassword(), "Each user should have its own password");
    }
}
