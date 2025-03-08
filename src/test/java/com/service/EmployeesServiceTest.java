package com.service;

import com.dao.EmployeesDAO;
import com.model.Employees;
import com.model.Addresses;
import com.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeesServiceTest {

    @Mock
    private EmployeesDAO employeesDAO;

    @InjectMocks
    private EmployeesService employeesService;

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
        when(employeesDAO.findAll()).thenReturn(employeesList);

        List<Employees> result = employeesService.getAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesDAO, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(employeesDAO.findById(1L)).thenReturn(Optional.of(employee));

        Employees result = employeesService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(employeesDAO, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeesDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeesService.getEmployeeById(1L));
        verify(employeesDAO, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeesByFirstName() {
        List<Employees> employeesList = Arrays.asList(employee);
        when(employeesDAO.findByFirstName("John")).thenReturn(employeesList);

        List<Employees> result = employeesService.getEmployeesByFirstName("John");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesDAO, times(1)).findByFirstName("John");
    }

    @Test
    void testGetEmployeesByPosition() {
        List<Employees> employeesList = Arrays.asList(employee);
        when(employeesDAO.findByPosition("Manager")).thenReturn(employeesList);

        List<Employees> result = employeesService.getEmployeesByPosition("Manager");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeesDAO, times(1)).findByPosition("Manager");
    }

    @Test
    void testAddEmployee() {
        when(employeesDAO.save(any(Employees.class))).thenReturn(employee);

        Employees result = employeesService.addEmployee(employee);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(employeesDAO, times(1)).save(any(Employees.class));
    }

    @Test
    void testUpdateEmployee() {
        when(employeesDAO.findById(1L)).thenReturn(Optional.of(employee));
        when(employeesDAO.save(any(Employees.class))).thenReturn(employee);

        Employees result = employeesService.updateEmployee(1L, employee);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(employeesDAO, times(1)).findById(1L);
        verify(employeesDAO, times(1)).save(any(Employees.class));
    }

    @Test
    void testUpdateEmployee_NotFound() {
        when(employeesDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeesService.updateEmployee(1L, employee));
        verify(employeesDAO, times(1)).findById(1L);
    }
}

