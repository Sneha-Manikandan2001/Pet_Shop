package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GroomingRelationshipTest {

    @Test
    public void testGettersAndSetters() {
        GroomingRelationship groomingRelationship = new GroomingRelationship();
        groomingRelationship.setId(1);

        GroomingServices groomingService = new GroomingServices();
        groomingRelationship.setGroomingService(groomingService);

        Pets pet = new Pets();
        groomingRelationship.setPet(pet);

        assertEquals(1, groomingRelationship.getId());
        assertEquals(groomingService, groomingRelationship.getGroomingService());
        assertEquals(pet, groomingRelationship.getPet());
    }
}

