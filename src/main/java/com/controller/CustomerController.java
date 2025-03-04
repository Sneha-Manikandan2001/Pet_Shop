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
        try {
            List<Customers> customers = customersDAO.findAll();
            if (customers == null || customers.isEmpty()) {
                throw new RuntimeException("No customers found");
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customer_id) {
        try {
            Optional<Customers> customer = customersDAO.findById(customer_id);
            if (customer.isPresent()) {
                return ResponseEntity.ok(customer.get());
            } else {
                throw new RuntimeException("Customer not found");
            }
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/name/{first_name}/{last_name}")
    public ResponseEntity<?> getCustomerByName(@PathVariable String first_name, @PathVariable String last_name) {
        try {
            Customers customer = customersDAO.findByFirstNameAndLastName(first_name, last_name);
            if (customer == null) {
                throw new RuntimeException("Customer not found");
            }
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/by_city/{city}")
    public ResponseEntity<?> getCustomersByCity(@PathVariable String city) {
        try {
            List<Customers> customers = customersDAO.findByCity(city);
            if (customers == null || customers.isEmpty()) {
                throw new RuntimeException("No customers found in the specified city");
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/by_state/{state}")
    public ResponseEntity<?> getCustomersByState(@PathVariable String state) {
        try {
            List<Customers> customers = customersDAO.findByState(state);
            if (customers == null || customers.isEmpty()) {
                throw new RuntimeException("No customers found in the specified state");
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/transactions/{customer_id}")
    public ResponseEntity<?> getCustomerTransactions(@PathVariable Long customer_id) {
        try {
            // Add logic to fetch customer transactions
            return ResponseEntity.ok("Customer transactions logic here");
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/transactions_status/{status}")
    public ResponseEntity<?> getCustomersByTransactionStatus(@PathVariable String status) {
        try {
            List<Customers> customers = customersDAO.findCustomersByTransactionStatus(status);
            if (customers == null || customers.isEmpty()) {
                throw new RuntimeException("No customers found with the specified transaction status");
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/no-transactions")
    public ResponseEntity<?> getCustomersWithoutTransactions() {
        try {
            List<Customers> customers = customersDAO.findCustomersWithoutTransactions();
            if (customers == null || customers.isEmpty()) {
                throw new RuntimeException("No customers found without transactions");
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return handleException();
        }
    }

    @GetMapping("/pets/{customer_id}")
    public ResponseEntity<?> getCustomerPets(@PathVariable Long customer_id) {
        try {
            // Add logic to fetch customer pets
            return ResponseEntity.ok("Customer pets logic here");
        } catch (Exception e) {
            return handleException();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customers customer) {
        try {
            if (customer == null) {
                throw new RuntimeException("Invalid customer data");
            }
            customersDAO.save(customer);
            Map<String, String> response = new HashMap<>();
            response.put("timeStamp", LocalDate.now().toString());
            response.put("message", "Customer added successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleException();
        }
    }

    @PutMapping("/update/{customer_id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customer_id, @RequestBody Customers customerDetails) {
        try {
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
                throw new RuntimeException("Customer not found");
            }
        } catch (Exception e) {
            return handleException();
        }
    }

    private ResponseEntity<Map<String, String>> handleException() {
        Map<String, String> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}