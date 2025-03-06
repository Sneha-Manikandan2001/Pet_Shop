package com.controller;

import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.model.PetCategories;
import com.service.PetCategoriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetCategoriesControllerTest {

    @InjectMocks
    private PetCategoriesController petCategoriesController;

    @Mock
    private PetCategoriesService petCategoriesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<PetCategories> categories = Arrays.asList(new PetCategories("Dogs"), new PetCategories("Cats"));
        when(petCategoriesService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<PetCategories>> response = petCategoriesController.getAllcategories();
        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetCategoryById() {
        PetCategories category = new PetCategories("Dogs");
        when(petCategoriesService.getCategoryById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<PetCategories> response = petCategoriesController.getCategoryById(1L);
        assertNotNull(response);
        assertEquals("Dogs", response.getBody().getName());
    }

    @Test
    void testGetCategoryByName() {
        List<PetCategories> categories = Arrays.asList(new PetCategories("Dogs"));
        when(petCategoriesService.getCategoryByName("Dogs")).thenReturn(categories);

        ResponseEntity<List<PetCategories>> response = petCategoriesController.getCategoryByName("Dogs");
        assertNotNull(response);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAddPetCategories() {
        PetCategories category = new PetCategories("Birds");
        when(petCategoriesService.addcate(category)).thenReturn(true);

        ResponseEntity<String> response = petCategoriesController.addPetCategories(category);
        assertNotNull(response);
        assertEquals("Address added successfully.", response.getBody());
    }

    @Test
    void testUpdateCategory() {
        PetCategories category = new PetCategories("Fish");
        when(petCategoriesService.getCategoryById(1L)).thenReturn(Optional.of(category));
        when(petCategoriesService.updateCategory(1L, category)).thenReturn(Optional.of(category));

        ResponseEntity<String> response = petCategoriesController.updateCategory(1L, category);
        assertNotNull(response);
        assertEquals("messages updated", response.getBody());
    }
}