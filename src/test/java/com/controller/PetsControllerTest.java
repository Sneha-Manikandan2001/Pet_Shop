package com.controller;

import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.model.Pets;
import com.model.FoodRelationship;
import com.model.GroomingRelationship;
import com.model.SupplierRelationship;
import com.model.VaccinationRelationship;
import com.model.Transactions;
import com.service.PetsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetsControllerTest {

    @InjectMocks
    private PetsController petsController;

    @Mock
    private PetsService petsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPets() {
        List<Pets> pets = Arrays.asList(new Pets(), new Pets());
        when(petsService.getAllPets()).thenReturn(pets);

        List<Pets> response = petsController.getAllPets();
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void testGetPetById() {
        Pets pet = new Pets();
        when(petsService.getPetById(1L)).thenReturn(Optional.of(pet));

        ResponseEntity<Pets> response = petsController.getPetById(1L);
        assertNotNull(response);
        assertEquals(pet, response.getBody());
    }

    @Test
    void testGetPetsByCategory() {
        List<Pets> pets = Arrays.asList(new Pets());
        when(petsService.getPetsByCategory("Dogs")).thenReturn(pets);

        ResponseEntity<List<Pets>> response = petsController.getPetsByCategory("Dogs");
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAddPet() {
        Pets pet = new Pets();
        when(petsService.addPet(pet)).thenReturn(true);

        ResponseEntity<Map<String, Object>> response = petsController.addPet(pet);
        assertNotNull(response);
        assertEquals("Pet added successfully", response.getBody().get("message"));
    }

    @Test
    void testUpdatePet() {
        Pets pet = new Pets();
        when(petsService.updatePet(1L, pet)).thenReturn(Optional.of(pet));

        ResponseEntity<String> response = petsController.updatePet(1L, pet);
        assertNotNull(response);
        assertEquals("Pet updated successfully.", response.getBody());
    }

    @Test
    void testGetGroomingServicesByPetId() {
        List<GroomingRelationship> services = Arrays.asList(new GroomingRelationship());
        when(petsService.getGroomingServicesByPetId(1L)).thenReturn(services);

        ResponseEntity<List<GroomingRelationship>> response = petsController.getGroomingServicesByPetId(1L);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetVaccinationsByPetId() {
        List<VaccinationRelationship> vaccinations = Arrays.asList(new VaccinationRelationship());
        when(petsService.getVaccinationsByPetId(1L)).thenReturn(vaccinations);

        ResponseEntity<List<VaccinationRelationship>> response = petsController.getVaccinationsByPetId(1L);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetFoodInfoByPetId() {
        List<FoodRelationship> foodInfo = Arrays.asList(new FoodRelationship());
        when(petsService.getFoodInfoByPetId(1L)).thenReturn(foodInfo);

        ResponseEntity<List<FoodRelationship>> response = petsController.getFoodInfoByPetId(1L);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetSuppliersByPetId() {
        List<SupplierRelationship> suppliers = Arrays.asList(new SupplierRelationship());
        when(petsService.getSuppliersByPetId(1L)).thenReturn(suppliers);

        ResponseEntity<List<SupplierRelationship>> response = petsController.getSuppliersByPetId(1L);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetTransactionHistoryByPetId() {
        List<Transactions> transactions = Arrays.asList(new Transactions());
        when(petsService.getTransactionHistoryByPetId(1L)).thenReturn(transactions);

        ResponseEntity<List<Transactions>> response = petsController.getTransactionHistoryByPetId(1L);
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }
}