package com.controller;

import com.model.Addresses;
import com.model.Employees;
import com.service.EmployeesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeesControllerTest {

    @Mock
    private EmployeesService employeesService;

    @InjectMocks
    private EmployeesController employeesController;

    private Employees employee;

    @BeforeEach
    void setUp() {
        employee = new Employees();
        employee.setEmployeeId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Manager");
        employee.setPhoneNumber("1234567890");
        employee.setEmail("john.doe@example.com");
        employee.setAddresses(new Addresses());
    }

    @Test
    void testGetAllEmployees() {
        List<Employees> employeesList = Arrays.asList(employee);
        when(employeesService.getAllEmployees()).thenReturn(employeesList);

        List<Employees> result = employeesController.getAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        when(employeesService.getEmployeeById(1L)).thenReturn(employee);

        Employees result = employeesController.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(employeesService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetEmployeesByFirstName() {
        List<Employees> employeesList = Arrays.asList(employee);
        when(employeesService.getEmployeesByFirstName("John")).thenReturn(employeesList);

        List<Employees> result = employeesController.getEmployeesByFirstName("John");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesService, times(1)).getEmployeesByFirstName("John");
    }

    @Test
    void testGetEmployeesByPosition() {
        List<Employees> employeesList = Arrays.asList(employee);
        when(employeesService.getEmployeesByPosition("Manager")).thenReturn(employeesList);

        List<Employees> result = employeesController.getEmployeesByPosition("Manager");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesService, times(1)).getEmployeesByPosition("Manager");
    }

    @Test
    void testAddEmployee() {
        when(employeesService.addEmployee(any(Employees.class))).thenReturn(employee);

        ResponseEntity<Map<String, Object>> response = employeesController.addEmployee(employee);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(employeesService, times(1)).addEmployee(any(Employees.class));
    }

    @Test
    void testUpdateEmployee() {
        when(employeesService.updateEmployee(eq(1L), any(Employees.class))).thenReturn(employee);

        ResponseEntity<Map<String, Object>> response = employeesController.updateEmployee(1L, employee);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(employeesService, times(1)).updateEmployee(eq(1L), any(Employees.class));
    }
}
