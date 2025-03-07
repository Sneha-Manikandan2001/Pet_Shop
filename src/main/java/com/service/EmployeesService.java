package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.Employees;
import com.dao.EmployeesDAO;
import com.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesDAO employeesDAO;

    public List<Employees> getAllEmployees() {
        return employeesDAO.findAll();
    }

    public Employees getEmployeeById(Long employeeId) {
        return employeesDAO.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<Employees> getEmployeesByFirstName(String firstName) {
        return employeesDAO.findByFirstName(firstName);
    }

    public List<Employees> getEmployeesByPosition(String position) {
        return employeesDAO.findByPosition(position);
    }

    public Employees addEmployee(Employees employee) {
        return employeesDAO.save(employee);
    }

    public Employees updateEmployee(Long employeeId, Employees employeeDetails) {
        Employees existingEmployee = employeesDAO.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setPosition(employeeDetails.getPosition());
        existingEmployee.setPhoneNumber(employeeDetails.getPhoneNumber());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setAddresses(employeeDetails.getAddresses());
        return employeesDAO.save(existingEmployee);
    }
}
