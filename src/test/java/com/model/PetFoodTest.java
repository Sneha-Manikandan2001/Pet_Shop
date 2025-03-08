package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PetFoodTest {

    @Test
    public void testGettersAndSetters() {
        PetFood petFood = new PetFood();
        petFood.setFoodId(1L);
        petFood.setName("Dog Food");
        petFood.setBrand("Brand A");
        petFood.setType("Dry");
        petFood.setQuantity(10);
        petFood.setPrice(19.99);

        assertEquals(1L, petFood.getFoodId());
        assertEquals("Dog Food", petFood.getName());
        assertEquals("Brand A", petFood.getBrand());
        assertEquals("Dry", petFood.getType());
        assertEquals(10, petFood.getQuantity());
        assertEquals(19.99, petFood.getPrice());
    }

    @Test
    public void testToString() {
        PetFood petFood = new PetFood();
        petFood.setFoodId(1L);
        petFood.setName("Dog Food");
        petFood.setBrand("Brand A");
        petFood.setType("Dry");
        petFood.setQuantity(10);
        petFood.setPrice(19.99);

        String expected = "PetFood{foodId=1, name='Dog Food', brand='Brand A', type='Dry', quantity=10, price=19.99}";
        assertEquals(expected, petFood.toString());
    }
}
