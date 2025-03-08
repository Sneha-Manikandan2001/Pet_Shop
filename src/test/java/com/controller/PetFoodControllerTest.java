package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.PetFood;
import com.service.PetFoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetFoodControllerTest {

    @Mock
    private PetFoodService petFoodService;

    @InjectMocks
    private PetFoodController petFoodController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPetFoods() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0));
        when(petFoodService.getAllPetFoods()).thenReturn(petFoods);

        List<PetFood> result = petFoodController.getAllPetFoods();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(petFoodService, times(1)).getAllPetFoods();
    }

    @Test
    public void testGetPetFoodById() {
        PetFood petFood = new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0);
        when(petFoodService.getPetFoodById(1L)).thenReturn(petFood);

        PetFood result = petFoodController.getPetFoodById(1L);
        assertNotNull(result);
        assertEquals("Food1", result.getName());
        verify(petFoodService, times(1)).getPetFoodById(1L);
    }

    @Test
    public void testSearchPetFoodsByName() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0));
        when(petFoodService.searchPetFoodsByName("Food1")).thenReturn(petFoods);

        List<PetFood> result = petFoodController.searchPetFoodsByName("Food1");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(petFoodService, times(1)).searchPetFoodsByName("Food1");
    }

    @Test
    public void testGetPetFoodsByType() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0));
        when(petFoodService.getPetFoodsByType("Type1")).thenReturn(petFoods);

        List<PetFood> result = petFoodController.getPetFoodsByType("Type1");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(petFoodService, times(1)).getPetFoodsByType("Type1");
    }

    @Test
    public void testGetPetFoodsByBrand() {
        List<PetFood> petFoods = Arrays.asList(new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0));
        when(petFoodService.getPetFoodsByBrand("Brand1")).thenReturn(petFoods);

        List<PetFood> result = petFoodController.getPetFoodsByBrand("Brand1");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(petFoodService, times(1)).getPetFoodsByBrand("Brand1");
    }

    @Test
    public void testAddPetFood() {
        PetFood petFood = new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0);
        doAnswer(invocation -> null).when(petFoodService).addPetFood(petFood);

        ResponseEntity<Map<String, Object>> response = petFoodController.addPetFood(petFood);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pet food added successfully", response.getBody().get("message"));
        verify(petFoodService, times(1)).addPetFood(petFood);
    }

    @Test
    public void testUpdatePetFood() {
        PetFood petFood = new PetFood(1, "Food1", "Brand1", "Type1", 10, 100.0);
        doAnswer(invocation -> null).when(petFoodService).updatePetFood(1L, petFood);

        ResponseEntity<Map<String, Object>> response = petFoodController.updatePetFood(1L, petFood);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pet food updated successfully", response.getBody().get("message"));
        verify(petFoodService, times(1)).updatePetFood(1L, petFood);
    }

    @Test
    public void testUpdatePetFoodQuantity() {
        doAnswer(invocation -> null).when(petFoodService).updatePetFoodQuantity(1L, 20);

        ResponseEntity<Map<String, Object>> response = petFoodController.updatePetFoodQuantity(1L, 20);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Pet food quantity updated successfully", response.getBody().get("message"));
        verify(petFoodService, times(1)).updatePetFoodQuantity(1L, 20);
    }
    @Test
    void testGetAllPetFoods_NoPetFoodsFound() {
        when(petFoodService.getAllPetFoods()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            petFoodController.getAllPetFoods();
        });

        assertEquals("No pet foods found", exception.getMessage());
    }

    @Test
    void testGetPetFoodById_NotFound() {
        when(petFoodService.getPetFoodById(1L)).thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            petFoodController.getPetFoodById(1L);
        });

        assertEquals("Pet id not found", exception.getMessage());
    }

    @Test
    void testSearchPetFoodsByName_NoPetFoodsFound() {
        when(petFoodService.searchPetFoodsByName("name")).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            petFoodController.searchPetFoodsByName("name");
        });

        assertEquals("No pet foods found with the given name", exception.getMessage());
    }

    @Test
    void testGetPetFoodsByType_NoPetFoodsFound() {
        when(petFoodService.getPetFoodsByType("type")).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            petFoodController.getPetFoodsByType("type");
        });

        assertEquals("No pet foods found with the given type", exception.getMessage());
    }

    @Test
    void testGetPetFoodsByBrand_NoPetFoodsFound() {
        when(petFoodService.getPetFoodsByBrand("brand")).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            petFoodController.getPetFoodsByBrand("brand");
        });

        assertEquals("No pet foods found with the given brand", exception.getMessage());
    }

    @Test
    void testAddPetFood_InvalidInput() {
        PetFood invalidPetFood = new PetFood();
        invalidPetFood.setName("");
        invalidPetFood.setBrand("");
        invalidPetFood.setType("");
        invalidPetFood.setQuantity(0);
        invalidPetFood.setPrice(0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            petFoodController.addPetFood(invalidPetFood);
        });

        assertEquals("Invalid", exception.getMessage());
    }

    @Test
    void testUpdatePetFood_InvalidInput() {
        PetFood invalidPetFood = new PetFood();
        invalidPetFood.setName("");
        invalidPetFood.setBrand("");
        invalidPetFood.setType("");
        invalidPetFood.setQuantity(0);
        invalidPetFood.setPrice(0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            petFoodController.updatePetFood(1L, invalidPetFood);
        });

        assertEquals("Invalid", exception.getMessage());
    }

    @Test
    void testUpdatePetFoodQuantity_InvalidQuantity() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            petFoodController.updatePetFoodQuantity(1L, 0);
        });

        assertEquals("Invalid quantity", exception.getMessage());
    }
}