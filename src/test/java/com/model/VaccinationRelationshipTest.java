package com.model;

import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.Test;
 
public class VaccinationRelationshipTest {
 
    @Test

    public void testGettersAndSetters() {

        VaccinationRelationship vaccinationRelationship = new VaccinationRelationship();

        vaccinationRelationship.setId(1);
 
        Vaccinations vaccination = new Vaccinations();

        vaccinationRelationship.setVaccination(vaccination);
 
        Pets pet = new Pets();

        vaccinationRelationship.setPet(pet);
 
        assertEquals(1, vaccinationRelationship.getId());

        assertEquals(vaccination, vaccinationRelationship.getVaccination());

        assertEquals(pet, vaccinationRelationship.getPet());

    }
 
    @Test

    public void testAdditionalMethods() {

        Vaccinations vaccination = new Vaccinations();

        vaccination.setName("Rabies");

        vaccination.setDescription("Rabies vaccination for dogs");

        vaccination.setPrice(25.0);

        vaccination.setAvailable(true);
 
        VaccinationRelationship vaccinationRelationship = new VaccinationRelationship(vaccination, new Pets());
 
        assertEquals("Rabies", vaccinationRelationship.getVaccinationName());

        assertEquals("Rabies vaccination for dogs", vaccinationRelationship.getVaccinationDescription());

        assertEquals(25.0, vaccinationRelationship.getVaccinationPrice());

        assertTrue(vaccinationRelationship.isVaccinationAvailable());

    }

}
 