package com.service;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.dao.CustomersDAO;
import com.model.Customers;
import com.model.TransactionStatus;
 
import java.util.List;
import java.util.Optional;
 
@Service
public class CustomersService {
 
    @Autowired
    private CustomersDAO customersDAO;
 
    public List<Customers> getAllCustomers() {
        return customersDAO.findAll();
    }
 
    public Optional<Customers> getCustomerById(Long customerId) {
        return customersDAO.findById(customerId);
    }
 
    public Customers getCustomerByName(String firstName, String lastName) {
        return customersDAO.findByFirstNameAndLastName(firstName, lastName);
    }
 
    public List<Customers> getCustomersByCity(String city) {
        return customersDAO.findByCity(city);
    }
 
    public List<Customers> getCustomersByState(String state) {
        return customersDAO.findByState(state);
    }
 
    public List<Customers> getCustomersByTransactionStatus(TransactionStatus status) {
        return customersDAO.findCustomersByTransactionStatus(status);
    }
 
    public List<Customers> getCustomersWithoutTransactions() {
        return customersDAO.findCustomersWithoutTransactions();
    }
 
    public Customers addCustomer(Customers customer) {
        return customersDAO.save(customer);
    }
 
    public Customers updateCustomer(Long customerId, Customers customerDetails) {
        Optional<Customers> customerOptional = customersDAO.findById(customerId);
        if (customerOptional.isPresent()) {
            Customers customer = customerOptional.get();
            customer.setFirstName(customerDetails.getFirstName());
            customer.setLastName(customerDetails.getLastName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setAddress(customerDetails.getAddress());
            return customersDAO.save(customer);
        } else {
            return null; // or throw an exception
        }
    }
}