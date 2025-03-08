package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
 
public class UserTest {
 
    @Test
    public void testUserConstructorAndGetters() {
        List<Role> roles = new ArrayList<>();
        User user = new User("testUser", "testPassword", roles);
 
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
        assertEquals(roles, user.getRoles());
    }
 
    @Test
    public void testSetters() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("newPassword");
 
        assertEquals("newUser", user.getUsername());
        assertEquals("newPassword", user.getPassword());
    }
}
 