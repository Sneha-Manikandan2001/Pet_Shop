package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthenticateUserTest {

    @Test
    public void testGettersAndSetters() {
        AuthenticateUser authUser = new AuthenticateUser();
        authUser.setUsername("testUser");
        authUser.setPassword("testPass");
        authUser.setRole("USER");

        assertEquals("testUser", authUser.getUsername());
        assertEquals("testPass", authUser.getPassword());
        assertEquals("USER", authUser.getRole());
    }
}
