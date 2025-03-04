package com.controller;

import com.dao.CustomersDAO;
import com.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomersDAO customersDAO;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customers> customers = customersDAO.findAll();
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customer_id) {
        Optional<Customers> customer = customersDAO.findById(customer_id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            throw new RuntimeException("Validation failed");
        }
    }

    @GetMapping("/name/{first_name}/{last_name}")
    public ResponseEntity<?> getCustomerByName(@PathVariable String first_name, @PathVariable String last_name) {
        Customers customer = customersDAO.findByFirstNameAndLastName(first_name, last_name);
        if (customer == null) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/by_city/{city}")
    public ResponseEntity<?> getCustomersByCity(@PathVariable String city) {
        List<Customers> customers = customersDAO.findByCity(city);
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/by_state/{state}")
    public ResponseEntity<?> getCustomersByState(@PathVariable String state) {
        List<Customers> customers = customersDAO.findByState(state);
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/transactions/{customer_id}")
    public ResponseEntity<?> getCustomerTransactions(@PathVariable Long customer_id) {
        // Add logic to fetch customer transactions
        return ResponseEntity.ok("Customer transactions logic here");
    }

    @GetMapping("/transactions_status/{status}")
    public ResponseEntity<?> getCustomersByTransactionStatus(@PathVariable String status) {
        List<Customers> customers = customersDAO.findCustomersByTransactionStatus(status);
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/no-transactions")
    public ResponseEntity<?> getCustomersWithoutTransactions() {
        List<Customers> customers = customersDAO.findCustomersWithoutTransactions();
        if (customers == null || customers.isEmpty()) {
            throw new RuntimeException("Validation failed");
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/pets/{customer_id}")
    public ResponseEntity<?> getCustomerPets(@PathVariable Long customer_id) {
        // Add logic to fetch customer pets
        return ResponseEntity.ok("Customer pets logic here");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customers customer) {
        if (customer == null) {
            throw new RuntimeException("Validation failed");
        }
        customersDAO.save(customer);
        Map<String, String> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Customer added successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{customer_id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customer_id, @RequestBody Customers customerDetails) {
        Optional<Customers> customerOptional = customersDAO.findById(customer_id);
        if (customerOptional.isPresent()) {
            Customers customer = customerOptional.get();
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setAddress(customerDetails.getAddress());
            customersDAO.save(customer);
            Map<String, String> response = new HashMap<>();
            response.put("timeStamp", LocalDate.now().toString());
            response.put("message", "Customer updated successfully");
            return ResponseEntity.ok(response);
        } else {
            throw new RuntimeException("Validation failed");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        Map<String, String> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}