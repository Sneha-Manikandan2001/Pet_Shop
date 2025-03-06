package com.controller;

import com.model.Addresses;
import com.model.Suppliers;
import com.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierControllerTest {

    @Mock
    private SupplierService supplierService;

    @InjectMocks
    private SupplierController supplierController;

    private Suppliers supplier;

    @BeforeEach
    void setUp() {
        supplier = new Suppliers();
        supplier.setSupplierId(1L);
        supplier.setName("Supplier Name");
        supplier.setContactPerson("Contact Person");
        supplier.setPhoneNumber("1234567890");
        supplier.setEmail("supplier@example.com");
        supplier.setAddress(new Addresses());
    }

    @Test
    void testGetAllSuppliers() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierService.getAllSuppliers()).thenReturn(suppliersList);

        List<Suppliers> result = supplierController.getAllSuppliers();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testGetSupplierById() {
        when(supplierService.getSupplierById(1L)).thenReturn(supplier);

        Suppliers result = supplierController.getSupplierById(1L);
        assertNotNull(result);
        assertEquals("Supplier Name", result.getName());
        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void testGetSuppliersByName() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierService.getSuppliersByName("Supplier Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierController.getSuppliersByName("Supplier Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierService, times(1)).getSuppliersByName("Supplier Name");
    }

    @Test
    void testGetSuppliersByCity() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierService.getSuppliersByCity("City Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierController.getSuppliersByCity("City Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierService, times(1)).getSuppliersByCity("City Name");
    }

    @Test
    void testGetSuppliersByState() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierService.getSuppliersByState("State Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierController.getSuppliersByState("State Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierService, times(1)).getSuppliersByState("State Name");
    }

    @Test
    void testAddSupplier() {
        when(supplierService.addSupplier(any(Suppliers.class))).thenReturn(supplier);

        ResponseEntity<Map<String, Object>> response = supplierController.addSupplier(supplier);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(supplierService, times(1)).addSupplier(any(Suppliers.class));
    }

    @Test
    void testUpdateSupplier() {
        when(supplierService.updateSupplier(eq(1L), any(Suppliers.class))).thenReturn(supplier);

        ResponseEntity<Map<String, Object>> response = supplierController.updateSupplier(1L, supplier);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(supplierService, times(1)).updateSupplier(eq(1L), any(Suppliers.class));
    }
}