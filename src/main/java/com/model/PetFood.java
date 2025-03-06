package com.model;
<<<<<<< HEAD

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="pet_food")
public class PetFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String type;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    // Default constructor
    public PetFood() {}

    // Parameterized constructor
    public PetFood(long foodId, String name, String brand, String type, Integer quantity, Double price) {
=======
 
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
 
@Entity
@Table(name ="pet_food")
public class PetFood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long foodId;
    private String name;
    private String brand;
    private String type;
    private int quantity;
    private double price;
    // Default constructor
    public PetFood() {}
    // Parameterized constructor
    public PetFood(long foodId, String name, String brand, String type, int quantity, double price) {
>>>>>>> origin/pets
        this.foodId = foodId;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }
<<<<<<< HEAD

=======
 
>>>>>>> origin/pets
    // Getters and Setters
    public long getFoodId() {
        return foodId;
    }
<<<<<<< HEAD

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

=======
 
    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
 
>>>>>>> origin/pets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
<<<<<<< HEAD

=======
>>>>>>> origin/pets
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }
<<<<<<< HEAD

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

=======
 
    public void setType(String type) {
        this.type = type;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
 
>>>>>>> origin/pets
    @Override
    public String toString() {
        return "PetFood{" +
                "foodId=" + foodId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
<<<<<<< HEAD
}
=======
}
 
>>>>>>> origin/pets
