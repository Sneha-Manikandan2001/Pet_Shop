package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.model.User;
import com.model.Role;
import com.service.UserService;
import com.service.RoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setRole_name("ROLE_USER"); // Default role for new users
        assignedRoles.add(userRole);
        userRole.setUser(user);

        user.setRoles(assignedRoles);

        try {
            userService.saveUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("status", "REGISTERSUCCESS");
            response.put("message", "User created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Log the exception for better debugging
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("status", "REGISTERFAIL");
            response.put("message", "Error creating user");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody User user) {
        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Create and assign roles
        List<Role> assignedRoles = new ArrayList<>();
        Role adminRole = new Role();
        adminRole.setRole_name("ROLE_ADMIN"); // Role for Admin
        assignedRoles.add(adminRole);
        adminRole.setUser(user);

        user.setRoles(assignedRoles);

        try {
            // Save the user with the admin role
            userService.saveUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("status", "REGISTERSUCCESS");
            response.put("message", "Admin created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            Map<String, String> response = new HashMap<>();
            response.put("status", "REGISTERFAIL");
            response.put("message", "Error creating Admin");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}