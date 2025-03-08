package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class SuppliersTest {
 
    @Test
    public void testSuppliersConstructorAndGetters() {
        Addresses address = new Addresses();
        Suppliers supplier = new Suppliers();
        supplier.setName("SupplierName");
        supplier.setContactPerson("ContactPerson");
        supplier.setPhoneNumber("1234567890");
        supplier.setEmail("supplier@example.com");
        supplier.setAddress(address);
 
        assertEquals("SupplierName", supplier.getName());
        assertEquals("ContactPerson", supplier.getContactPerson());
        assertEquals("1234567890", supplier.getPhoneNumber());
        assertEquals("supplier@example.com", supplier.getEmail());
        assertEquals(address, supplier.getAddress());
    }
}
