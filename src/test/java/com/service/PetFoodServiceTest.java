package com.service;

import com.dao.PetFoodDAO;
import com.model.PetFood;
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

class PetFoodServiceTest {

    @Mock
    private PetFoodDAO petFoodDAO;

    @InjectMocks
    private PetFoodService petFoodService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPetFoods() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(), new PetFood());
        when(petFoodDAO.findAll()).thenReturn(petFoods);

        List<PetFood> result = petFoodService.getAllPetFoods();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPetFoodById_Found() {
        PetFood petFood = new PetFood();
        when(petFoodDAO.findById(1L)).thenReturn(Optional.of(petFood));

        PetFood result = petFoodService.getPetFoodById(1L);
        assertNotNull(result);
    }

    @Test
    void testGetPetFoodById_NotFound() {
        when(petFoodDAO.findById(1L)).thenReturn(Optional.empty());

        PetFood result = petFoodService.getPetFoodById(1L);
        assertNull(result);
    }

    @Test
    void testSearchPetFoodsByName() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(), new PetFood());
        when(petFoodDAO.findByName("name")).thenReturn(petFoods);

        List<PetFood> result = petFoodService.searchPetFoodsByName("name");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPetFoodsByType() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(), new PetFood());
        when(petFoodDAO.findByType("type")).thenReturn(petFoods);

        List<PetFood> result = petFoodService.getPetFoodsByType("type");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetPetFoodsByBrand() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(), new PetFood());
        when(petFoodDAO.findByBrand("brand")).thenReturn(petFoods);

        List<PetFood> result = petFoodService.getPetFoodsByBrand("brand");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testAddPetFood() {
        PetFood petFood = new PetFood();
        when(petFoodDAO.save(petFood)).thenReturn(petFood);

        PetFood result = petFoodService.addPetFood(petFood);
        assertNotNull(result);
    }

    @Test
    void testUpdatePetFood_Found() {
        PetFood petFood = new PetFood();
        when(petFoodDAO.existsById(1L)).thenReturn(true);
        when(petFoodDAO.save(petFood)).thenReturn(petFood);

        PetFood result = petFoodService.updatePetFood(1L, petFood);
        assertNotNull(result);
    }

    @Test
    void testUpdatePetFood_NotFound() {
        PetFood petFood = new PetFood();
        when(petFoodDAO.existsById(1L)).thenReturn(false);

        PetFood result = petFoodService.updatePetFood(1L, petFood);
        assertNull(result);
    }

    @Test
    void testUpdatePetFoodQuantity_Found() {
        PetFood petFood = new PetFood();
        when(petFoodDAO.findById(1L)).thenReturn(Optional.of(petFood));
        when(petFoodDAO.save(petFood)).thenReturn(petFood);

        PetFood result = petFoodService.updatePetFoodQuantity(1L, 10);
        assertNotNull(result);
        assertEquals(10, result.getQuantity());
    }

    @Test
    void testUpdatePetFoodQuantity_NotFound() {
        when(petFoodDAO.findById(1L)).thenReturn(Optional.empty());

        PetFood result = petFoodService.updatePetFoodQuantity(1L, 10);
        assertNull(result);
    }
}