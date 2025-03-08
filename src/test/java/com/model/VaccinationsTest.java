package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VaccinationsTest {

    @Test
    public void testGettersAndSetters() {
        Vaccinations vaccination = new Vaccinations();
        vaccination.setVaccinationId(1L);
        vaccination.setName("Rabies");
        vaccination.setDescription("Rabies vaccination for dogs");
        vaccination.setPrice(25.0);
        vaccination.setAvailable(true);

        assertEquals(1L, vaccination.getVaccinationId());
        assertEquals("Rabies", vaccination.getName());
        assertEquals("Rabies vaccination for dogs", vaccination.getDescription());
        assertEquals(25.0, vaccination.getPrice());
        assertTrue(vaccination.isAvailable());
    }

    @Test
    public void testToString() {
        Vaccinations vaccination = new Vaccinations();
        vaccination.setVaccinationId(1L);
        vaccination.setName("Rabies");
        vaccination.setDescription("Rabies vaccination for dogs");
        vaccination.setPrice(25.0);
        vaccination.setAvailable(true);

        String expected = "Vaccinations [vaccinationId=1, name=Rabies, description=Rabies vaccination for dogs, price=25.0, available=true]";
        assertEquals(expected, vaccination.toString());
    }
}
