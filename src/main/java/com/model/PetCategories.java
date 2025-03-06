package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
<<<<<<< HEAD
 
@Entity
<<<<<<< HEAD

@Table(name = "pet_categories")

public class PetCategories {
=======
@Table(name = "pet_categories")
public class PetCategories {
 
>>>>>>> origin/customer
=======
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "pet_categories")
public class PetCategories {

>>>>>>> origin/pets
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long categoryId;
<<<<<<< HEAD
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
 
=======
    @NotEmpty
    @Column(name = "name")
    private String name;
    
    // Constructors
    public PetCategories() {}

    public PetCategories(String name) {
        this.name = name;
    }

>>>>>>> origin/pets
    // Getters and Setters
    public long getCategoryId() {
        return categoryId;
    }
<<<<<<< HEAD
 
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
=======

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

>>>>>>> origin/pets
    @Override
    public String toString() {
        return "PetCategory [categoryId=" + categoryId + ", name=" + name + "]";
    }
<<<<<<< HEAD
<<<<<<< HEAD

}
 
 
=======
}
>>>>>>> origin/customer
=======

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
>>>>>>> origin/pets
