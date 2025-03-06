package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
<<<<<<< HEAD

@Table(name = "pet_categories")

public class PetCategories {
=======
@Table(name = "pet_categories")
public class PetCategories {
 
>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long categoryId;
<<<<<<< HEAD

=======
 
>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
    @Column(name = "name")
    private String name;
 
    // Constructors
    public PetCategories() {}
 
    public PetCategories(String name) {
        this.name = name;
    }
 
    // Getters and Setters
    public long getCategoryId() {
        return categoryId;
    }
 
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    @Override
    public String toString() {
        return "PetCategory [categoryId=" + categoryId + ", name=" + name + "]";
    }
<<<<<<< HEAD

}
 
 
=======
}
>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
