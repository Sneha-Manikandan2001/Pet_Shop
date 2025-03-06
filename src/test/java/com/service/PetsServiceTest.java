package com.service;

import com.dao.PetsDAO;
import com.model.Pets;
import com.model.FoodRelationship;
import com.model.GroomingRelationship;
import com.model.SupplierRelationship;
import com.model.VaccinationRelationship;
import com.model.Transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetsServiceTest {

    @InjectMocks
    private PetsService petsService;

    @Mock
    private PetsDAO petsDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPets() {
        List<Pets> pets = Arrays.asList(new Pets(), new Pets());
        when(petsDAO.findAll()).thenReturn(pets);

        List<Pets> result = petsService.getAllPets();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPetById() {
        Pets pet = new Pets();
        when(petsDAO.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pets> result = petsService.getPetById(1L);
        assertTrue(result.isPresent());
        assertEquals(pet, result.get());
    }

    @Test
    void testGetPetsByCategory() {
        List<Pets> pets = Arrays.asList(new Pets());
        when(petsDAO.findByCategoryName("Dogs")).thenReturn(pets);

        List<Pets> result = petsService.getPetsByCategory("Dogs");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddPet() {
        Pets pet = new Pets();
        when(petsDAO.save(pet)).thenReturn(pet);

        boolean result = petsService.addPet(pet);
        assertTrue(result);
    }

    @Test
    void testUpdatePet() {
        Pets pet = new Pets();
        when(petsDAO.findById(1L)).thenReturn(Optional.of(pet));
        when(petsDAO.save(pet)).thenReturn(pet);

        Optional<Pets> result = petsService.updatePet(1L, pet);
        assertTrue(result.isPresent());
        assertEquals(pet, result.get());
    }

    @Test
    void testGetGroomingServicesByPetId() {
        List<GroomingRelationship> services = Arrays.asList(new GroomingRelationship());
        when(petsDAO.findGroomingServicesByPetId(1L)).thenReturn(services);

        List<GroomingRelationship> result = petsService.getGroomingServicesByPetId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetVaccinationsByPetId() {
        List<VaccinationRelationship> vaccinations = Arrays.asList(new VaccinationRelationship());
        when(petsDAO.findVaccinationsByPetId(1L)).thenReturn(vaccinations);

        List<VaccinationRelationship> result = petsService.getVaccinationsByPetId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetFoodInfoByPetId() {
        List<FoodRelationship> foodInfo = Arrays.asList(new FoodRelationship());
        when(petsDAO.findFoodInfoByPetId(1L)).thenReturn(foodInfo);

        List<FoodRelationship> result = petsService.getFoodInfoByPetId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetSuppliersByPetId() {
        List<SupplierRelationship> suppliers = Arrays.asList(new SupplierRelationship());
        when(petsDAO.findSuppliersByPetId(1L)).thenReturn(suppliers);

        List<SupplierRelationship> result = petsService.getSuppliersByPetId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetTransactionHistoryByPetId() {
        List<Transactions> transactions = Arrays.asList(new Transactions());
        when(petsDAO.findTransactionHistoryByPetId(1L)).thenReturn(transactions);

        List<Transactions> result = petsService.getTransactionHistoryByPetId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}