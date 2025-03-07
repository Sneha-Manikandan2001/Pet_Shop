package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Employees;
import java.util.List;

@Repository
public interface EmployeesDAO extends JpaRepository<Employees, Long> {
    List<Employees> findByFirstName(String firstName);
    List<Employees> findByPosition(String position);
}