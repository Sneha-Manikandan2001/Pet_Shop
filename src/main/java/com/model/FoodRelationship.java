package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_food_relationship")
public class FoodRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private PetFood food;

    // Constructors, getters, and setters
    public FoodRelationship() {}

    public FoodRelationship(PetFood food, Pets pet) {
        this.food = food;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PetFood getFood() {
        return food;
    }

    public void setFood(PetFood food) {
        this.food = food;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }
}
