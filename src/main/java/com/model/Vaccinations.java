package com.model;
 
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;
 
@Entity

@Table(name= "vaccinations")

public class Vaccinations {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long vaccinationId;

	private String name;

	private String description;

	private double price;

	private boolean available;

	public Vaccinations() {}

	public Vaccinations(long vaccinationId, String name, String description, double price, boolean available) {

		super();

		this.vaccinationId = vaccinationId;

		this.name = name;

		this.description = description;

		this.price = price;

		this.available = available;

	}

	public long getVaccinationId() {

		return vaccinationId;

	}
 
	public void setVaccinationId(long vaccinationId) {

		this.vaccinationId = vaccinationId;

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

	public boolean isAvailable() {

		return available;

	}

	public void setAvailable(boolean available) {

		this.available = available;

	}
 
	@Override

	public String toString() {

		return "Vaccinations [vaccinationId=" + vaccinationId + ", name=" + name + ", description=" + description

				+ ", price=" + price + ", available=" + available + "]";

	}

}

 