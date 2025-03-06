package com.model;
 
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
        this.foodId = foodId;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }
 
    // Getters and Setters
    public long getFoodId() {
        return foodId;
    }
 
    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }
 
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
}
 