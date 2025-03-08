package com.controller;
 
import com.model.User;
import com.model.Role;
import com.service.UserService;
import com.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
 
    @Mock
    private UserService userService;
 
    @Mock
    private RoleService roleService;
 
    @Mock
    private PasswordEncoder passwordEncoder;
 
    @InjectMocks
    private UserController userController;
 
    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userService.getAllUsers()).thenReturn(users);
 
        ResponseEntity<List<User>> response = userController.getAllUsers();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }
 
    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setPassword("password");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
 
        ResponseEntity<?> response = userController.registerUser(user);
 
        verify(userService, times(1)).saveUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("REGISTERSUCCESS", ((Map<String, String>) response.getBody()).get("status"));
    }
 
    @Test
    public void testRegisterAdmin() {
        User user = new User();
        user.setPassword("password");
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
 
        ResponseEntity<?> response = userController.registerAdmin(user);
 
        verify(userService, times(1)).saveUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("REGISTERSUCCESS", ((Map<String, String>) response.getBody()).get("status"));
    }
}
 