package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PetCategoriesTest {

    @Test
    public void testGettersAndSetters() {
        PetCategories petCategory = new PetCategories();
        petCategory.setCategoryId(1L);
        petCategory.setName("Dog");

        assertEquals(1L, petCategory.getCategoryId());
        assertEquals("Dog", petCategory.getName());
    }

    @Test
    public void testToString() {
        PetCategories petCategory = new PetCategories();
        petCategory.setCategoryId(1L);
        petCategory.setName("Dog");

        String expected = "PetCategories [categoryId=1, name=Dog]";
        assertEquals(expected, petCategory.toString());
    }
}

