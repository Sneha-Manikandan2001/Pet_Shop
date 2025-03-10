package com.service;
 
import com.dao.CustomersDAO;
import com.model.Customers;
import com.model.TransactionStatus;
import com.model.Transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
public class CustomersServiceTest {
 
    @Mock
    private CustomersDAO customersDAO;
 
    @InjectMocks
    private CustomersService customersService;
 
    private Customers customer;
    private Transactions transaction;
 
    @BeforeEach
    public void setUp() {
        customer = new Customers();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
 
        transaction = new Transactions();
        transaction.setTransactionId(1L);
        transaction.setCustomer(customer);
        transaction.setTransactionDate(new java.util.Date());
        transaction.setAmount(100.0);
        transaction.setTransactionStatus(TransactionStatus.Success);
    }
 
    @Test
    public void testGetAllCustomers() {
        when(customersDAO.findAll()).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customersService.getAllCustomers();
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomerById() {
        when(customersDAO.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customers> foundCustomer = customersService.getCustomerById(1L);
        assertTrue(foundCustomer.isPresent());
        assertEquals("John", foundCustomer.get().getFirstName());
    }
 
    @Test
    public void testGetCustomerById_NotFound() {
        when(customersDAO.findById(1L)).thenReturn(Optional.empty());
        Optional<Customers> foundCustomer = customersService.getCustomerById(1L);
        assertFalse(foundCustomer.isPresent());
    }
 
    @Test
    public void testGetCustomerByName() {
        when(customersDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(customer);
        Customers foundCustomer = customersService.getCustomerByName("John", "Doe");
        assertNotNull(foundCustomer);
        assertEquals("John", foundCustomer.getFirstName());
    }
 
    @Test
    public void testGetCustomerByName_NotFound() {
        when(customersDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(null);
        Customers foundCustomer = customersService.getCustomerByName("John", "Doe");
        assertNull(foundCustomer);
    }
 
    @Test
    public void testGetCustomersByCity() {
        when(customersDAO.findByCity("New York")).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customersService.getCustomersByCity("New York");
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByCity_NotFound() {
        when(customersDAO.findByCity("New York")).thenReturn(Collections.emptyList());
        List<Customers> customers = customersService.getCustomersByCity("New York");
        assertTrue(customers.isEmpty());
    }
 
    @Test
    public void testGetCustomersByState() {
        when(customersDAO.findByState("NY")).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customersService.getCustomersByState("NY");
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByState_NotFound() {
        when(customersDAO.findByState("NY")).thenReturn(Collections.emptyList());
        List<Customers> customers = customersService.getCustomersByState("NY");
        assertTrue(customers.isEmpty());
    }
 
    @Test
    public void testGetCustomersByTransactionStatus_Success() {
        when(customersDAO.findCustomersByTransactionStatus(TransactionStatus.Success)).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customersService.getCustomersByTransactionStatus(TransactionStatus.Success);
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersByTransactionStatus_Failed() {
        when(customersDAO.findCustomersByTransactionStatus(TransactionStatus.Failed)).thenReturn(Collections.emptyList());
        List<Customers> customers = customersService.getCustomersByTransactionStatus(TransactionStatus.Failed);
        assertTrue(customers.isEmpty());
    }
 
    @Test
    public void testGetCustomersWithoutTransactions() {
        when(customersDAO.findCustomersWithoutTransactions()).thenReturn(Arrays.asList(customer));
        List<Customers> customers = customersService.getCustomersWithoutTransactions();
        assertNotNull(customers);
        assertEquals(1, customers.size());
    }
 
    @Test
    public void testGetCustomersWithoutTransactions_NotFound() {
        when(customersDAO.findCustomersWithoutTransactions()).thenReturn(Collections.emptyList());
        List<Customers> customers = customersService.getCustomersWithoutTransactions();
        assertTrue(customers.isEmpty());
    }
 
    @Test
    public void testAddCustomer() {
        when(customersDAO.save(customer)).thenReturn(customer);
        Customers addedCustomer = customersService.addCustomer(customer);
        assertNotNull(addedCustomer);
        assertEquals("John", addedCustomer.getFirstName());
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
 
        Customers result = customersService.updateCustomer(1L, updatedCustomer);
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
    }
 
    @Test
    public void testUpdateCustomer_NotFound() {
        when(customersDAO.findById(1L)).thenReturn(Optional.empty());
        Customers updatedCustomer = new Customers();
        Customers result = customersService.updateCustomer(1L, updatedCustomer);
        assertNull(result);
    }
}