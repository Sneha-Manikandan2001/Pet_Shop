package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
public class Employees {

    @Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", position=" + position + ", hireDate=" + hireDate + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", addresses=" + addresses + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

	@NotEmpty
    private String firstName;
	
	@NotEmpty
    private String lastName;
	
	@NotEmpty
    private String position;

	@NotEmpty
    @Temporal(TemporalType.DATE)//Maps the field to a SQL DATE type, storing only the date part (year, month, day).
    private Date hireDate;
	
	@NotEmpty
    private String phoneNumber;

	@NotEmpty
    private String email;
	
	@NotEmpty
    @ManyToOne
    private Addresses addresses;

    // Getters and Setters

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses address) {
        this.addresses = address;
    }
}