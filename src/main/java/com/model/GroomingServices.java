package com.model;
 
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
 
@Entity

@Table(name="grooming_services")

public class GroomingServices {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long serviceId;
 
    @Override

	public String toString() {

		return "GroomingServices [serviceId=" + serviceId + ", name=" + name + ", description=" + description

				+ ", price=" + price + ", available=" + available + "]";

	}
 
	private String name;
 
    private String description;
 
    private double price;
 
    private Boolean available = true;
 
    // Getters and Setters
 
    public Long getServiceId() {

        return serviceId;

    }
 
    public void setServiceId(Long serviceId) {

        this.serviceId = serviceId;

    }
 
    public String getName() {

        return name;

    }
 
    public void setName(String name) {

        this.name = name;

    }
 
    public String getDescription() {

        return description;

    }
 
    public void setDescription(String description) {

        this.description = description;

    }
 
    public double getPrice() {

        return price;

    }
 
    public void setPrice(double price) {

        this.price = price;

    }
 
    public Boolean getAvailable() {

        return available;

    }
 
    public void setAvailable(Boolean available) {

        this.available = available;

    }

}
 