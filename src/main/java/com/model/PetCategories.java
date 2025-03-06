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
 
>>>>>>> origin/customer
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long categoryId;
<<<<<<< HEAD

=======
 
>>>>>>> origin/customer
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
>>>>>>> origin/customer
