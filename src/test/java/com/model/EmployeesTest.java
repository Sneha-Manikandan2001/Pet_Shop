package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Date;

public class EmployeesTest {

    @Test
    public void testGettersAndSetters() {
        Employees employee = new Employees();
        employee.setEmployeeId(1L);
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setPosition("Manager");
        employee.setPhoneNumber("0987654321");
        employee.setEmail("jane.doe@example.com");

        Date hireDate = new Date(System.currentTimeMillis());
        employee.setHireDate(hireDate);

        Addresses address = new Addresses();
        employee.setAddresses(address);

        RoleType role = RoleType.ADMIN;
        employee.setRole(role);

        assertEquals(1L, employee.getEmployeeId());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("Manager", employee.getPosition());
        assertEquals("0987654321", employee.getPhoneNumber());
        assertEquals("jane.doe@example.com", employee.getEmail());
        assertEquals(hireDate, employee.getHireDate());
        assertEquals(address, employee.getAddresses());
        assertEquals(role, employee.getRole());
    }

    @Test
    public void testToString() {
        Employees employee = new Employees();
        employee.setEmployeeId(1L);
        employee.setFirstName("Jane");
        employee.setLastName("Doe");
        employee.setPosition("Manager");
        employee.setPhoneNumber("0987654321");
        employee.setEmail("jane.doe@example.com");

        Date hireDate = new Date(System.currentTimeMillis());
        employee.setHireDate(hireDate);

        Addresses address = new Addresses();
        employee.setAddresses(address);

        RoleType role = RoleType.ADMIN;
        employee.setRole(role);

        String expected = "Employees [employeeId=1, firstName=Jane, lastName=Doe, position=Manager, phoneNumber=0987654321, email=jane.doe@example.com, hireDate=" + hireDate + ", addresses=" + address + ", role=ADMIN]";
        assertEquals(expected, employee.toString());
    }
}
