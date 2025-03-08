package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomersTest {

    @Test
    public void testGettersAndSetters() {
        Customers customer = new Customers();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");

        Addresses address = new Addresses();
        customer.setAddress(address);

        List<Transactions> transactions = new ArrayList<>();
        customer.setTransactions(transactions);

        assertEquals(1L, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals(address, customer.getAddress());
        assertEquals(transactions, customer.getTransactions());
    }
}

