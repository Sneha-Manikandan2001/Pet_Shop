package com.controller;

import com.dao.AddressesDAO;
import com.model.Addresses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressesControllerTest {

    @Mock
    private AddressesDAO addressesDAO;

    @InjectMocks
    private AddressesController addressesController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAddresses() {
        List<Addresses> addresses = new ArrayList<>();
        addresses.add(new Addresses());
        when(addressesDAO.findAll()).thenReturn(addresses);

        ResponseEntity<List<Addresses>> response = addressesController.getAllAddresses();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetAddressById() {
        Addresses address = new Addresses();
        when(addressesDAO.findById(1L)).thenReturn(Optional.of(address));

        ResponseEntity<Addresses> response = addressesController.getAddressById(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testAddAddress() {
        Addresses address = new Addresses();
        when(addressesDAO.save(address)).thenReturn(address);

        ResponseEntity<Map<String, Object>> response = addressesController.addAddress(address);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Address added successfully", response.getBody().get("message"));
    }

    @Test
    public void testUpdateAddress() {
        Addresses address = new Addresses();
        when(addressesDAO.findById(1L)).thenReturn(Optional.of(address));
        when(addressesDAO.save(address)).thenReturn(address);

        Addresses addressDetails = new Addresses();
        ResponseEntity<Map<String, Object>> response = addressesController.updateAddress(1L, addressDetails);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Address updated successfully", response.getBody().get("message"));
    }
}