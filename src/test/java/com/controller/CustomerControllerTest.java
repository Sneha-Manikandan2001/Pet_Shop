package com.controller;
 
import com.dao.CustomersDAO;
import com.exception.ResourceNotFoundException;
import com.model.Customers;
import com.model.Pets;
import com.model.TransactionStatus;
import com.model.Transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
 
    @Mock
    private CustomersDAO customersDAO;
 
    @InjectMocks
    private CustomerController customerController;
 
    private Customers customer;
    private Transactions transaction;
    private Pets pet;
 
    @BeforeEach
    public void setUp() {
        customer = new Customers();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
 
        pet = new Pets();
        pet.setPetId(1L);
        pet.setName("Buddy");
 
        transaction = new Transactions();
        transaction.setTransactionId(1L);
        transaction.setCustomer(customer);
        transaction.setPet(pet);
        transaction.setTransactionDate(new Date());
        transaction.setAmount(100.0);
        transaction.setTransactionStatus(TransactionStatus.Success);
    }
 
    @Test
    public void testGetAllCustomers() {
        when(customersDAO.findAll()).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customerController.getAllCustomers();
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomerById() {
        when(customersDAO.findById(1L)).thenReturn(Optional.of(customer));
        Customers foundCustomer = customerController.getCustomerById(1L);
        assertNotNull(foundCustomer);
        assertEquals("John", foundCustomer.getFirstName());
    }
 
    @Test
    public void testGetCustomerById_NotFound() {
        when(customersDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomerById(1L));
    }
 
    @Test
    public void testGetCustomerByName() {
        when(customersDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(customer);
        Customers foundCustomer = customerController.getCustomerByName("John", "Doe");
        assertNotNull(foundCustomer);
        assertEquals("John", foundCustomer.getFirstName());
    }
 
    @Test
    public void testGetCustomerByName_NotFound() {
        when(customersDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomerByName("John", "Doe"));
    }
 
    @Test
    public void testGetCustomersByCity() {
        when(customersDAO.findByCity("New York")).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customerController.getCustomersByCity("New York");
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByCity_NotFound() {
        when(customersDAO.findByCity("New York")).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomersByCity("New York"));
    }
 
    @Test
    public void testGetCustomersByState() {
        when(customersDAO.findByState("NY")).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customerController.getCustomersByState("NY");
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByState_NotFound() {
        when(customersDAO.findByState("NY")).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomersByState("NY"));
    }
 
    @Test
    public void testGetCustomerTransactions() {
        when(customersDAO.findTransactionsByCustomerId(1L)).thenReturn(Arrays.asList(transaction));
        ResponseEntity<?> response = customerController.getCustomerTransactions(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
 
    @Test
    public void testGetCustomerTransactions_NotFound() {
        when(customersDAO.findTransactionsByCustomerId(1L)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomerTransactions(1L));
    }
 
    @Test
    public void testGetCustomersByTransactionStatus_Success() {
        when(customersDAO.findCustomersByTransactionStatus(TransactionStatus.Success)).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customerController.getCustomersByTransactionStatus("Success");
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByTransactionStatus_Failed() {
        when(customersDAO.findCustomersByTransactionStatus(TransactionStatus.Failed)).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomersByTransactionStatus("Failed"));
    }
 
    @Test
    public void testGetCustomersWithoutTransactions() {
        when(customersDAO.findCustomersWithoutTransactions()).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customerController.getCustomersWithoutTransactions();
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersWithoutTransactions_NotFound() {
        when(customersDAO.findCustomersWithoutTransactions()).thenReturn(Collections.emptyList());
        assertThrows(ResourceNotFoundException.class, () -> customerController.getCustomersWithoutTransactions());
    }
 
    @Test
    public void testGetCustomerPets() {
        when(customersDAO.findPetsByCustomerFirstName("John")).thenReturn(Optional.of(Arrays.asList(pet)));
        ResponseEntity<?> response = customerController.getCustomerPets("John");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
 
    @Test
    public void testAddCustomer() {
        when(customersDAO.save(customer)).thenReturn(customer);
        ResponseEntity<Map<String, Object>> response = customerController.addCustomer(customer);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Customer added successfully", response.getBody().get("message"));
    }
 
    @Test
    public void testUpdateCustomer() {
        when(customersDAO.findById(1L)).thenReturn(Optional.of(customer));
        when(customersDAO.save(any(Customers.class))).thenReturn(customer);
        Customers updatedCustomer = new Customers();
        updatedCustomer.setFirstName("Jane");
        updatedCustomer.setLastName("Doe");
        updatedCustomer.setEmail("jane.doe@example.com");
        updatedCustomer.setPhoneNumber("0987654321");
 
        ResponseEntity<Map<String, Object>> response = customerController.updateCustomer(1L, updatedCustomer);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer updated successfully", response.getBody().get("message"));
    }
 
    @Test
    public void testUpdateCustomer_NotFound() {
        when(customersDAO.findById(1L)).thenReturn(Optional.empty());
        Customers updatedCustomer = new Customers();
        assertThrows(RuntimeException.class, () -> customerController.updateCustomer(1L, updatedCustomer));
    }
}
 