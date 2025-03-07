package com.service;

import com.dao.CustomersDAO;
import com.model.Customers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomersServiceTest {

    @Mock
    private CustomersDAO customersDAO;

    @InjectMocks
    private CustomersService customersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customers> customers = List.of(new Customers());
        when(customersDAO.findAll()).thenReturn(customers);

        List<Customers> result = customersService.getAllCustomers();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetCustomerById() {
        Customers customer = new Customers();
        when(customersDAO.findById(1L)).thenReturn(Optional.of(customer));

        Optional<Customers> result = customersService.getCustomerById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    public void testGetCustomerByName() {
        Customers customer = new Customers();
        when(customersDAO.findByFirstNameAndLastName("John", "Doe")).thenReturn(customer);

        Customers result = customersService.getCustomerByName("John", "Doe");
        assertNotNull(result);
    }

    @Test
    public void testGetCustomersByCity() {
        List<Customers> customers = List.of(new Customers());
        when(customersDAO.findByCity("New York")).thenReturn(customers);

        List<Customers> result = customersService.getCustomersByCity("New York");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetCustomersByState() {
        List<Customers> customers = List.of(new Customers());
        when(customersDAO.findByState("California")).thenReturn(customers);

        List<Customers> result = customersService.getCustomersByState("California");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetCustomersByTransactionStatus() {
        List<Customers> customers = List.of(new Customers());
        when(customersDAO.findCustomersByTransactionStatus("Completed")).thenReturn(customers);

        List<Customers> result = customersService.getCustomersByTransactionStatus("Completed");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetCustomersWithoutTransactions() {
        List<Customers> customers = List.of(new Customers());
        when(customersDAO.findCustomersWithoutTransactions()).thenReturn(customers);

        List<Customers> result = customersService.getCustomersWithoutTransactions();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddCustomer() {
        Customers customer = new Customers();
        when(customersDAO.save(customer)).thenReturn(customer);

        Customers result = customersService.addCustomer(customer);
        assertNotNull(result);
    }

    @Test
    public void testUpdateCustomer() {
        Customers customer = new Customers();
        when(customersDAO.findById(1L)).thenReturn(Optional.of(customer));
        when(customersDAO.save(customer)).thenReturn(customer);

        Customers customerDetails = new Customers();
        Customers result = customersService.updateCustomer(1L, customerDetails);
        assertNotNull(result);
    }
}