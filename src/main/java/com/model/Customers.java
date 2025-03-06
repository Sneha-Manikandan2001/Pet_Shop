package com.model;
<<<<<<< HEAD
 
 
=======

import java.util.List;
>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.Table;
 
@Entity
@Table(name = "customers")
public class Customers {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
 
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
 
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses address;
 
    // Getters and Setters
 
    public Long getCustomerId() {
        return customerId;
    }
 
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    public Addresses getAddress() {
        return address;
    }
 
=======
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses address;

    @OneToMany(mappedBy = "customer")
    private List<Transactions> transactions;

    // Getters and Setters

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Addresses getAddress() {
        return address;
    }

>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
    public void setAddress(Addresses address) {
        this.address = address;
    }

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}
=======
    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }
>>>>>>> 0f2ae15546aee2d4e311a1639e0ebbe6050ecfa5
}