package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Addresses {
 
    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    private Long addressId;
 
    private String street;
    private String city;
    private String state;
    private String zipCode;
 
    // Getters and Setters
 
    public Long getAddressId() {
        return addressId;
    }
 
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
 
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        this.street = street;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
    public String getZipCode() {
        return zipCode;
    }
 
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	@Override
	public String toString() {
		return "Addresses [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}
    

}