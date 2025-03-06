package com.service;

import com.dao.SupplierDAO;
import com.model.Addresses;
import com.model.Suppliers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {

    @Mock
    private SupplierDAO supplierDAO;

    @InjectMocks
    private SupplierService supplierService;

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
        when(supplierDAO.findAll()).thenReturn(suppliersList);

        List<Suppliers> result = supplierService.getAllSuppliers();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierDAO, times(1)).findAll();
    }

    @Test
    void testGetSupplierById() {
        when(supplierDAO.findById(1L)).thenReturn(Optional.of(supplier));

        Suppliers result = supplierService.getSupplierById(1L);
        assertNotNull(result);
        assertEquals("Supplier Name", result.getName());
        verify(supplierDAO, times(1)).findById(1L);
    }

    @Test
    void testGetSuppliersByName() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierDAO.findByName("Supplier Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierService.getSuppliersByName("Supplier Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierDAO, times(1)).findByName("Supplier Name");
    }

    @Test
    void testGetSuppliersByCity() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierDAO.findByCity("City Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierService.getSuppliersByCity("City Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierDAO, times(1)).findByCity("City Name");
    }

    @Test
    void testGetSuppliersByState() {
        List<Suppliers> suppliersList = Arrays.asList(supplier);
        when(supplierDAO.findByState("State Name")).thenReturn(suppliersList);

        List<Suppliers> result = supplierService.getSuppliersByState("State Name");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierDAO, times(1)).findByState("State Name");
    }

    @Test
    void testAddSupplier() {
        when(supplierDAO.save(any(Suppliers.class))).thenReturn(supplier);

        Suppliers result = supplierService.addSupplier(supplier);
        assertNotNull(result);
        assertEquals("Supplier Name", result.getName());
        verify(supplierDAO, times(1)).save(any(Suppliers.class));
    }

    @Test
    void testUpdateSupplier() {
        when(supplierDAO.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplierDAO.save(any(Suppliers.class))).thenReturn(supplier);

        Suppliers result = supplierService.updateSupplier(1L, supplier);
        assertNotNull(result);
        assertEquals("Supplier Name", result.getName());
        verify(supplierDAO, times(1)).findById(1L);
        verify(supplierDAO, times(1)).save(any(Suppliers.class));
    }
}