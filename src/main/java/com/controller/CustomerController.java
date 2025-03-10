package com.controller;
 
import com.exception.ResourceNotFoundException;
import com.dao.CustomersDAO;
import com.model.Customers;
import com.model.Pets;
import com.model.TransactionStatus;
import com.model.Transactions;
 
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
@CrossOrigin("*")
@RequestMapping("/api/v1/customers")
public class CustomerController {
 
    @Autowired
    private CustomersDAO customersDAO;
 
    @GetMapping
    public List<Customers> getAllCustomers() {
        List<Customers> customers = customersDAO.findAll();
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found");
        }
        return customers;
    }
 
    @GetMapping("/{customer_id}")
    public Customers getCustomerById(@PathVariable Long customer_id) {
        Optional<Customers> customer = customersDAO.findById(customer_id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new ResourceNotFoundException("Customer not found");
        }
    }
 
    @GetMapping("/name/{first_name}/{last_name}")
    public Customers getCustomerByName(@PathVariable String first_name, @PathVariable String last_name) {
        Customers customer = customersDAO.findByFirstNameAndLastName(first_name, last_name);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with the given name");
        }
        return customer;
    }
 
    @GetMapping("/by_city/{city}")
    public List<Customers> getCustomersByCity(@PathVariable String city) {
        List<Customers> customers = customersDAO.findByCity(city);
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found in the given city");
        }
        return customers;
    }
 
    @GetMapping("/by_state/{state}")
    public List<Customers> getCustomersByState(@PathVariable String state) {
        List<Customers> customers = customersDAO.findByState(state);
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found in the given state");
        }
        return customers;
    }
 
    @GetMapping("/transactions/{customer_id}")
    public ResponseEntity<?> getCustomerTransactions(@PathVariable Long customer_id) {
        List<Transactions> transactions = customersDAO.findTransactionsByCustomerId(customer_id);
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for the given customer");
        }
        return ResponseEntity.ok(transactions);
    }
 
    @GetMapping("/transactions_status/{status}")
//    public List<Customers> getCustomersByTransactionStatus(@PathVariable String status) {
//    	
//        List<Customers> customers = customersDAO.findCustomersByTransactionStatus(TransactionStatus.Success);
//        if (customers == null || customers.isEmpty()) {
//            throw new ResourceNotFoundException("No customers found with the given transaction status");
//        }
//        return customers;
//    }
    public List<Customers> getCustomersByTransactionStatus(@PathVariable String status) {
        TransactionStatus transactionStatus;
        if ("Success".equalsIgnoreCase(status)) {
            transactionStatus = TransactionStatus.Success;
        } else if ("Failed".equalsIgnoreCase(status)) {
            transactionStatus = TransactionStatus.Failed;
        } else {
            throw new ResourceNotFoundException("Invalid transaction status");
        }
 
        List<Customers> customers = customersDAO.findCustomersByTransactionStatus(transactionStatus);
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found with the given transaction status");
        }
        return customers;
    }
 
    @GetMapping("/no-transactions")
    public List<Customers> getCustomersWithoutTransactions() {
        List<Customers> customers = customersDAO.findCustomersWithoutTransactions();
        if (customers == null || customers.isEmpty()) {
            throw new ResourceNotFoundException("No customers found without transactions");
        }
        return customers;
    }
//    @GetMapping("/pets/{customer_id}")
//    public ResponseEntity<?> getCustomerPets(@PathVariable Long customer_id) {
//        // Add logic to fetch customer pets
//        return ResponseEntity.ok("Customer pets logic here");
//    }
 
    @GetMapping("/pets/{firstName}")
    public ResponseEntity<?> getCustomerPets(@PathVariable String firstName) 
    {
       List<Object> pets = customersDAO.findPetsByCustomerFirstName(firstName).get();

//    	if (pets == null || pets.isEmpty()) {
//            throw new ResourceNotFoundException("No pets found for the given customer");
//        }
        return ResponseEntity.ok(pets);
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addCustomer(@RequestBody Customers customer) {
        if (customer == null) {
            throw new RuntimeException("Invalid customer data");
        }
        customersDAO.save(customer);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Customer added successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

 
        
    @PutMapping("/update/{customer_id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Long customer_id, @RequestBody Customers customerDetails) {
        Optional<Customers> customerOptional = customersDAO.findById(customer_id);
        if (customerOptional.isPresent()) {
            Customers customer = customerOptional.get();
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setAddress(customerDetails.getAddress());
            customersDAO.save(customer);
            Map<String, Object> response = new HashMap<>();
            response.put("timeStamp", LocalDate.now().toString());
            response.put("message", "Customer updated successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}