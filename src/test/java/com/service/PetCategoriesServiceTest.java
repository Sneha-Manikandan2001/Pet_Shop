package com.service;

import com.dao.PetCategoriesDAO;
import com.model.PetCategories;
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

class PetCategoriesServiceTest {

    @InjectMocks
    private PetCategoriesService petCategoriesService;

    @Mock
    private PetCategoriesDAO petCategoriesDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<PetCategories> categories = Arrays.asList(new PetCategories("Dogs"), new PetCategories("Cats"));
        when(petCategoriesDAO.findAll()).thenReturn(categories);

        List<PetCategories> result = petCategoriesService.getAllCategories();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetCategoryById() {
        PetCategories category = new PetCategories("Dogs");
        when(petCategoriesDAO.findById(1L)).thenReturn(Optional.of(category));

        Optional<PetCategories> result = petCategoriesService.getCategoryById(1L);
        assertTrue(result.isPresent());
        assertEquals("Dogs", result.get().getName());
    }

    @Test
    void testGetCategoryByName() {
        List<PetCategories> categories = Arrays.asList(new PetCategories("Dogs"));
        when(petCategoriesDAO.findByName("Dogs")).thenReturn(categories);

        List<PetCategories> result = petCategoriesService.getCategoryByName("Dogs");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testAddCategory() {
        PetCategories category = new PetCategories("Birds");
        when(petCategoriesDAO.save(category)).thenReturn(category);

        PetCategories result = petCategoriesService.addCategory(category);
        assertNotNull(result);
        assertEquals("Birds", result.getName());
    }

    @Test
    void testAddCate() {
        PetCategories category = new PetCategories("Birds");
        when(petCategoriesDAO.save(category)).thenReturn(category);

        boolean result = petCategoriesService.addcate(category);
        assertTrue(result);
    }

    @Test
    void testUpdateCategory() {
        PetCategories category = new PetCategories("Fish");
        when(petCategoriesDAO.findById(1L)).thenReturn(Optional.of(category));
        when(petCategoriesDAO.save(category)).thenReturn(category);

        Optional<PetCategories> result = petCategoriesService.updateCategory(1L, category);
        assertTrue(result.isPresent());
        assertEquals("Fish", result.get().getName());
    }
}