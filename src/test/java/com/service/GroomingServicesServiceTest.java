package com.service;

import com.dao.GroomingServicesDAO;
import com.model.GroomingServices;
import com.exception.ResourceNotFoundException;
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
public class GroomingServicesServiceTest {

    @Mock
    private GroomingServicesDAO groomingServicesDAO;

    @InjectMocks
    private GroomingServicesService groomingServicesService;

    private GroomingServices service;

    @BeforeEach
    void setUp() {
        service = new GroomingServices();
        service.setServiceId(1L);
        service.setName("Service Name");
        service.setDescription("Service Description");
        service.setPrice(100.0);
        service.setAvailable(true);
    }

    @Test
    void testGetAllGroomingServices() {
        List<GroomingServices> servicesList = Arrays.asList(service);
        when(groomingServicesDAO.findAll()).thenReturn(servicesList);

        List<GroomingServices> result = groomingServicesService.getAllGroomingServices();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(groomingServicesDAO, times(1)).findAll();
    }

    @Test
    void testGetGroomingServiceById() {
        when(groomingServicesDAO.findById(1L)).thenReturn(Optional.of(service));

        GroomingServices result = groomingServicesService.getGroomingServiceById(1L);
        assertNotNull(result);
        assertEquals("Service Name", result.getName());
        verify(groomingServicesDAO, times(1)).findById(1L);
    }

    @Test
    void testGetGroomingServiceById_NotFound() {
        when(groomingServicesDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groomingServicesService.getGroomingServiceById(1L));
        verify(groomingServicesDAO, times(1)).findById(1L);
    }

    @Test
    void testGetAvailableGroomingServices() {
        List<GroomingServices> servicesList = Arrays.asList(service);
        when(groomingServicesDAO.findByAvailable(true)).thenReturn(servicesList);

        List<GroomingServices> result = groomingServicesService.getAvailableGroomingServices();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(groomingServicesDAO, times(1)).findByAvailable(true);
    }

    @Test
    void testGetUnavailableGroomingServices() {
        service.setAvailable(false);
        List<GroomingServices> servicesList = Arrays.asList(service);
        when(groomingServicesDAO.findByAvailable(false)).thenReturn(servicesList);

        List<GroomingServices> result = groomingServicesService.getUnavailableGroomingServices();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(groomingServicesDAO, times(1)).findByAvailable(false);
    }

    @Test
    void testAddGroomingService() {
        when(groomingServicesDAO.save(any(GroomingServices.class))).thenReturn(service);

        GroomingServices result = groomingServicesService.addGroomingService(service);
        assertNotNull(result);
        assertEquals("Service Name", result.getName());
        verify(groomingServicesDAO, times(1)).save(any(GroomingServices.class));
    }

    @Test
    void testUpdateGroomingService() {
        when(groomingServicesDAO.findById(1L)).thenReturn(Optional.of(service));
        when(groomingServicesDAO.save(any(GroomingServices.class))).thenReturn(service);

        GroomingServices result = groomingServicesService.updateGroomingService(1L, service);
        assertNotNull(result);
        assertEquals("Service Name", result.getName());
        verify(groomingServicesDAO, times(1)).findById(1L);
        verify(groomingServicesDAO, times(1)).save(any(GroomingServices.class));
    }

    @Test
    void testUpdateGroomingService_NotFound() {
        when(groomingServicesDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> groomingServicesService.updateGroomingService(1L, service));
        verify(groomingServicesDAO, times(1)).findById(1L);
    }
}