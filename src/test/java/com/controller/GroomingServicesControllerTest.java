package com.controller;

import com.model.GroomingServices;
import com.service.GroomingServicesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroomingServicesControllerTest {

    @Mock
    private GroomingServicesService groomingServicesService;

    @InjectMocks
    private GroomingServicesController groomingServicesController;

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
        when(groomingServicesService.getAllGroomingServices()).thenReturn(servicesList);

        ResponseEntity<List<GroomingServices>> response = groomingServicesController.getAllGroomingServices();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(groomingServicesService, times(1)).getAllGroomingServices();
    }

    @Test
    void testGetGroomingService() {
        when(groomingServicesService.getGroomingServiceById(1L)).thenReturn(service);

        ResponseEntity<GroomingServices> response = groomingServicesController.getGroomingService(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(service, response.getBody());
        verify(groomingServicesService, times(1)).getGroomingServiceById(1L);
    }

    @Test
    void testGetAvailableGroomingServices() {
        List<GroomingServices> servicesList = Arrays.asList(service);
        when(groomingServicesService.getAvailableGroomingServices()).thenReturn(servicesList);

        ResponseEntity<List<GroomingServices>> response = groomingServicesController.getAvailableGroomingServices();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(groomingServicesService, times(1)).getAvailableGroomingServices();
    }

    @Test
    void testGetUnavailableGroomingServices() {
        service.setAvailable(false);
        List<GroomingServices> servicesList = Arrays.asList(service);
        when(groomingServicesService.getUnavailableGroomingServices()).thenReturn(servicesList);

        ResponseEntity<List<GroomingServices>> response = groomingServicesController.getUnavailableGroomingServices();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(groomingServicesService, times(1)).getUnavailableGroomingServices();
    }

    @Test
    void testAddGroomingService() {
        when(groomingServicesService.addGroomingService(any(GroomingServices.class))).thenReturn(service);

        ResponseEntity<Map<String, Object>> response = groomingServicesController.addGroomingService(service);
        assertEquals(201, response.getStatusCodeValue());
        verify(groomingServicesService, times(1)).addGroomingService(any(GroomingServices.class));
    }

    @Test
    void testUpdateGroomingService() {
        when(groomingServicesService.updateGroomingService(eq(1L), any(GroomingServices.class))).thenReturn(service);

        ResponseEntity<Map<String, Object>> response = groomingServicesController.updateGroomingService(1L, service);
        assertEquals(200, response.getStatusCodeValue());
        verify(groomingServicesService, times(1)).updateGroomingService(eq(1L), any(GroomingServices.class));
    }
}

