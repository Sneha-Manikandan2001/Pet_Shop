package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.Employees;
import java.util.List;

public interface EmployeesDAO extends JpaRepository<Employees, Long> {
    List<Employees> findByFirstName(String firstName);
    List<Employees> findByPosition(String position);
}