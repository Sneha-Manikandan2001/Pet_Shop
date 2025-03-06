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
        PetFood petFood1 = new PetFood(1L, "Food1", "Brand1", "Type1", 10, 100.0);
        PetFood petFood2 = new PetFood(2L, "Food2", "Brand2", "Type2", 20, 200.0);
        when(petFoodDAO.findAll()).thenReturn(Arrays.asList(petFood1, petFood2));

        List<PetFood> petFoods = petFoodService.getAllPetFoods();
        assertEquals(2, petFoods.size());
        verify(petFoodDAO, times(1)).findAll();
    }

    @Test
    void testGetPetFoodById() {
        PetFood petFood = new PetFood(1L, "Food1", "Brand1", "Type1", 10, 100.0);
        when(petFoodDAO.findById(1L)).thenReturn(Optional.of(petFood));

        PetFood result = petFoodService.getPetFoodById(1L);
        assertNotNull(result);
        assertEquals("Food1", result.getName());
        verify(petFoodDAO, times(1)).findById(1L);
    }

    @Test
    void testAddPetFood() {
        PetFood petFood = new PetFood(1L, "Food1", "Brand1", "Type1", 10, 100.0);
        when(petFoodDAO.save(petFood)).thenReturn(petFood);

        PetFood result = petFoodService.addPetFood(petFood);
        assertNotNull(result);
        assertEquals("Food1", result.getName());
        verify(petFoodDAO, times(1)).save(petFood);
    }

    @Test
    void testUpdatePetFoodQuantity() {
        PetFood petFood = new PetFood(1L, "Food1", "Brand1", "Type1", 10, 100.0);
        when(petFoodDAO.findById(1L)).thenReturn(Optional.of(petFood));
        when(petFoodDAO.save(petFood)).thenReturn(petFood);

        PetFood result = petFoodService.updatePetFoodQuantity(1L, 20);
        assertNotNull(result);
        assertEquals(20, result.getQuantity());
        verify(petFoodDAO, times(1)).findById(1L);
        verify(petFoodDAO, times(1)).save(petFood);
    }
}