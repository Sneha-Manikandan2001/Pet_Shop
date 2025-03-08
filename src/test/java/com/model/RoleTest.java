package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class RoleTest {
 
    @Test
    public void testRoleConstructorAndGetters() {
        User user = new User();
        Role role = new Role("Admin");
        role.setUser(user);
 
        assertEquals("Admin", role.getRole_name());
        assertEquals(user, role.getUser());
}}
