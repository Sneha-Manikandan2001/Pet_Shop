package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GroomingServicesTest {

    @Test
    public void testGettersAndSetters() {
        GroomingServices groomingService = new GroomingServices();
        groomingService.setServiceId(1L);
        groomingService.setName("Basic Grooming");
        groomingService.setDescription("Includes bath and haircut");
        groomingService.setPrice(29.99);
        groomingService.setAvailable(true);

        assertEquals(1L, groomingService.getServiceId());
        assertEquals("Basic Grooming", groomingService.getName());
        assertEquals("Includes bath and haircut", groomingService.getDescription());
        assertEquals(29.99, groomingService.getPrice());
        assertTrue(groomingService.getAvailable());
    }

    @Test
    public void testToString() {
        GroomingServices groomingService = new GroomingServices();
        groomingService.setServiceId(1L);
        groomingService.setName("Basic Grooming");
        groomingService.setDescription("Includes bath and haircut");
        groomingService.setPrice(29.99);
        groomingService.setAvailable(true);

        String expected = "GroomingServices [serviceId=1, name=Basic Grooming, description=Includes bath and haircut, price=29.99, available=true]";
        assertEquals(expected, groomingService.toString());
    }
}


