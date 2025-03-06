package com.model;
 
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
 
@Entity
@Table(name = "suppliers")
public class Suppliers {
 
    @Id

    @GeneratedValue

    private Long supplierId;
 
    private String name;

    private String contactPerson;

    private String phoneNumber;

    private String email;
 
    @ManyToOne

    @JoinColumn(name = "address_id") //joins addresses table with this table

    private Addresses address;
 
    // Getters and Setters

    public Long getSupplierId() {

        return supplierId;

    }
 
    public void setSupplierId(Long supplierId) {

        this.supplierId = supplierId;

    }
 
    public String getName() {

        return name;

    }
 
    public void setName(String name) {

        this.name = name;

    }
 
    public String getContactPerson() {

        return contactPerson;

    }
 
    public void setContactPerson(String contactPerson) {

        this.contactPerson = contactPerson;

    }
 
    public String getPhoneNumber() {

        return phoneNumber;

    }
 
    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

    }
 
    public String getEmail() {

        return email;

    }
 
    public void setEmail(String email) {

        this.email = email;

    }
 
    public Addresses getAddress() {

        return address;

    }
 
    public void setAddress(Addresses address) {

        this.address = address;

    }

}
 
 