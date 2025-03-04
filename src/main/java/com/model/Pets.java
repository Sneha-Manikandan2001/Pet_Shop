package com.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "pets")
public class Pets {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private long petId;
 
    @Column(name = "name")
    private String name;
 
    @Column(name = "breed")
    private String breed;
 
    @Column(name = "age")
    private int age;
 
    @Column(name = "price")
    private double price;
 
    @Column(name = "description")
    private String description;
 
    @Column(name = "image_url")
    private String imageUrl;
 
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private PetCategories category;
 
    // Constructors
    public Pets() {}
 
    public Pets(String name, String breed, int age, double price, PetCategories category, String description, String imageUrl) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.price = price;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }
 
    // Getters and Setters
    public long getPetId() {
        return petId;
    }
 
    public void setPetId(int petId) {
        this.petId = petId;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getBreed() {
        return breed;
    }
 
    public void setBreed(String breed) {
        this.breed = breed;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getImageUrl() {
        return imageUrl;
    }
 
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
 
    public PetCategories getCategory() {
        return category;
    }
 
    public void setCategory(PetCategories category) {
        this.category = category;
    }
 
    @Override
    public String toString() {
        return "Pet [petId=" + petId + ", name=" + name + ", breed=" + breed + ", age=" + age + ", price=" + price + 
               ", category=" + category + ", description=" + description + ", imageUrl=" + imageUrl + "]";
    }
}
