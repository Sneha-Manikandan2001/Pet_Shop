package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_supplier_relationship")
public class SupplierRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suppliers supplier;

    // Constructors, getters, and setters
    public SupplierRelationship() {}

    public SupplierRelationship(Suppliers supplier, Pets pet) {
        this.supplier = supplier;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }
}


