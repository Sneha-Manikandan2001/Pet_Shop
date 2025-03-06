package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employees;
import com.dao.EmployeesDAO;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesDAO employeesDAO;

    public List<Employees> getAllEmployees() {
        return employeesDAO.findAll();
    }

    public Employees getEmployeeById(Long employeeId) {
        Optional<Employees> employee = employeesDAO.findById(employeeId);
        return employee.orElse(null);
    }

    public List<Employees> getEmployeesByFirstName(String firstName) {
        return employeesDAO.findByFirstName(firstName);
    }

    public List<Employees> getEmployeesByPosition(String positionName) {
        return employeesDAO.findByPosition(positionName);
    }

    public Employees addEmployee(Employees employee) {
        return employeesDAO.save(employee);
    }

    public Employees updateEmployee(Long employeeId, Employees employeeDetails) {
        Employees employee = getEmployeeById(employeeId);
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setPosition(employeeDetails.getPosition());
            employee.setHireDate(employeeDetails.getHireDate());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            employee.setEmail(employeeDetails.getEmail());
            employee.setAddresses(employeeDetails.getAddresses());
            return employeesDAO.save(employee);
        }
        return null;
    }
}