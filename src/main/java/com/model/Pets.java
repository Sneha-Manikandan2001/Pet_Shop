package com.model;
 
import java.util.ArrayList;
import java.util.List;
 
import org.hibernate.annotations.Type;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name = "pets")
public class Pets {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pet_id;
 
    @NotEmpty
    private String name;
 
    @NotEmpty
    private String breed;
 
    @NotNull
    private int age;
 
    @NotNull
    private double price;
 
    @NotEmpty
    private String description;
    private byte[] image_url;
 
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private PetCategories category;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
 
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodRelationship> foodRelationships = new ArrayList<>();
 
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroomingRelationship> groomingRelationships = new ArrayList<>();
 
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierRelationship> supplierRelationships = new ArrayList<>();
 
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VaccinationRelationship> vaccinationRelationships = new ArrayList<>();
 
    @ManyToOne
    private Transactions transaction;
 
    public Pets() {}
 
    public Pets(String name, String breed, int age, double price, PetCategories category, String description, byte[] image_url) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image_url = image_url;
    }
 
    // Getters and Setters
    public long getPetId() {
        return pet_id;
    }
 
    public void setPetId(long petId) {
        this.pet_id = petId;
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
 
    public byte[] getImage() {
        return image_url;
    }
 
    public void setImage(byte[] image) {
        this.image_url = image;
    }
 
    public PetCategories getCategory() {
        return category;
    }
 
    public void setCategory(PetCategories category) {
        this.category = category;}
       @Override
    public String toString() {
        return "Pet [petId=" + pet_id + ", name=" + name + ", breed=" + breed + ", age=" + age + ", price=" + price + 
               ", category=" + category + ", description=" + description + ", image=" + image_url + "]";
    }
}