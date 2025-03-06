package com.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_grooming_relationship")
public class GroomingRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private GroomingServices groomingService;

    // Constructors, getters, and setters
    public GroomingRelationship() {}

    public GroomingRelationship(GroomingServices groomingService, Pets pet) {
        this.groomingService = groomingService;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroomingServices getGroomingService() {
        return groomingService;
    }

    public void setGroomingService(GroomingServices groomingService) {
        this.groomingService = groomingService;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }
}
