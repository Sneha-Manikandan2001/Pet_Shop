package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_vaccination_relationship")
public class VaccinationRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
    
    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    private Vaccinations vaccination;

    // Constructors, getters, and setters
    public VaccinationRelationship() {}

    public VaccinationRelationship(Vaccinations vaccination, Pets pet) {
        this.vaccination = vaccination;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vaccinations getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccinations vaccination) {
        this.vaccination = vaccination;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    // Additional methods to get vaccination details
    public String getVaccinationName() {
        return vaccination.getName();
    }

    public String getVaccinationDescription() {
        return vaccination.getDescription();
    }

    public double getVaccinationPrice() {
        return vaccination.getPrice();
    }

    public boolean isVaccinationAvailable() {
        return vaccination.isAvailable();
    }
}