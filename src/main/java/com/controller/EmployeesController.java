package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.Employees;
import com.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping
    public List<Employees> getAllEmployees() {
        List<Employees> employees = employeesService.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found");
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employees getEmployeeById(@PathVariable Long employeeId) {
        Employees employee = employeesService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found");
        }
        return employee;
    }

    @GetMapping("/firstname/{firstName}")
    public List<Employees> getEmployeesByFirstName(@PathVariable String firstName) {
        List<Employees> employees = employeesService.getEmployeesByFirstName(firstName);
        if (employees == null || employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found with the given first name");
        }
        return employees;
    }

    @GetMapping("/position/{positionName}")
    public List<Employees> getEmployeesByPosition(@PathVariable String positionName) {
        List<Employees> employees = employeesService.getEmployeesByPosition(positionName);
        if (employees == null || employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found with the given position");
        }
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employees employee) {
        if (employee == null) {
            throw new RuntimeException("Invalid");
        }
        employeesService.addEmployee(employee);

        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Employee added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable Long employeeId, @RequestBody Employees employee) {
        if (employee == null) {
            throw new RuntimeException("Invalid");
        }
        employeesService.updateEmployee(employeeId, employee);

        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Employee updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
