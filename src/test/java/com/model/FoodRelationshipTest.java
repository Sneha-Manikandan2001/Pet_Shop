package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FoodRelationshipTest {

    @Test
    public void testGettersAndSetters() {
        FoodRelationship foodRelationship = new FoodRelationship();
        foodRelationship.setId(1);

        PetFood food = new PetFood();
        foodRelationship.setFood(food);

        Pets pet = new Pets();
        foodRelationship.setPet(pet);

        assertEquals(1, foodRelationship.getId());
        assertEquals(food, foodRelationship.getFood());
        assertEquals(pet, foodRelationship.getPet());
    }
}
