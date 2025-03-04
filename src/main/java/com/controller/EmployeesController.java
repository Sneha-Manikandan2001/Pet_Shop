package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Employees;
import com.service.EmployeesService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeesService.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new RuntimeException("No employees found");
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employee_id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long employee_id) {
        Employees employee = employeesService.getEmployeeById(employee_id);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Employees>> getEmployeesByFirstName(@PathVariable String firstName) {
        List<Employees> employees = employeesService.getEmployeesByFirstName(firstName);
        if (employees == null || employees.isEmpty()) {
            throw new RuntimeException("No employees found with the given first name");
        }
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/position/{position_name}")
    public ResponseEntity<List<Employees>> getEmployeesByPosition(@PathVariable String position_name) {
        List<Employees> employees = employeesService.getEmployeesByPosition(position_name);
        if (employees == null || employees.isEmpty()) {
            throw new RuntimeException("No employees found with the given position");
        }
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employees employee) {
        if (employee == null) {
            throw new RuntimeException("Invalid employee data");
        }
        employeesService.addEmployee(employee);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Employee added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable Long employee_id, @RequestBody Employees employeeDetails) {
        if (employeeDetails == null) {
            throw new RuntimeException("Invalid employee data");
        }
        employeesService.updateEmployee(employee_id, employeeDetails);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Employee updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}