package com.service;

import com.dao.AddressesDAO;
import com.model.Addresses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressesServiceTest {

    @Mock
    private AddressesDAO addressesDAO;

    @InjectMocks
    private AddressesService addressesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAddresses() {
        List<Addresses> addresses = List.of(new Addresses());
        when(addressesDAO.findAll()).thenReturn(addresses);

        List<Addresses> result = addressesService.getAllAddresses();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetAddressById() {
        Addresses address = new Addresses();
        when(addressesDAO.findById(1L)).thenReturn(Optional.of(address));

        Optional<Addresses> result = addressesService.getAddressById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    public void testAddAddress() {
        Addresses address = new Addresses();
        when(addressesDAO.save(address)).thenReturn(address);

        Addresses result = addressesService.addAddress(address);
        assertNotNull(result);
    }

    @Test
    public void testUpdateAddress() {
        Addresses address = new Addresses();
        when(addressesDAO.findById(1L)).thenReturn(Optional.of(address));
        when(addressesDAO.save(address)).thenReturn(address);

        Addresses addressDetails = new Addresses();
        Addresses result = addressesService.updateAddress(1L, addressDetails);
        assertNotNull(result);
    }
}

