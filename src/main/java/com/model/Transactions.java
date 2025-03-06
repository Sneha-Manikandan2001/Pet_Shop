package com.model;
<<<<<<< HEAD

<<<<<<< HEAD

=======
>>>>>>> origin/customer
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
<<<<<<< HEAD
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;

    @NotNull
    @Temporal(TemporalType.DATE)//Maps the field to a SQL DATE type, storing only the date part (year, month, day).
    private Date transactionDate;

    @NotNull
    private double amount;
    
    @NotNull
    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)	//to specify how an enum type should be persisted in the database
    private TransactionStatus transactionStatus;

=======
 
 
@Entity
public class Transactions {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
 
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
 
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pets pet;
 
    @Temporal(TemporalType.DATE)//Maps the field to a SQL DATE type, storing only the date part (year, month, day).
=======
 
 
import java.util.Date;

import com.model.TransactionStatus;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;

import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;

import jakarta.persistence.TemporalType;
 

 
@Entity
@Table(name="transactions")
public class Transactions {
 
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long transactionId;
 
    @ManyToOne

    @JoinColumn(name = "customer_id")

    private Customers customer;
 
    @ManyToOne

    @JoinColumn(name = "pet_id")

    private Pets pet;
 
    @Temporal(TemporalType.DATE)//Maps the field to a SQL DATE type, storing only the date part (year, month, day).

>>>>>>> origin/pets
    private Date transactionDate;
 
    private double amount;
 
    @Column(name = "transaction_status")
<<<<<<< HEAD
    @Enumerated(EnumType.STRING)	//to specify how an enum type should be persisted in the database
    private TransactionStatus transactionStatus;
//    enum TransactionStatus {
//        SUCCESS,
//        FAILED
//    }
    private String status;
 
>>>>>>> origin/customer
    // Getters and Setters
    public Long getTransactionId() {
        return transactionId;
    }
<<<<<<< HEAD

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

=======
 
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
 
    public Customers getCustomer() {
        return customer;
    }
 
    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
 
    public Pets getPet() {
        return pet;
    }
 
    public void setPet(Pets pet) {
        this.pet = pet;
    }
 
    public Date getTransactionDate() {
        return transactionDate;
    }
 
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
 
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }
 
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
 
>>>>>>> origin/customer
	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", pet=" + pet + ", transactionDate=" + transactionDate
				+ ", amount=" + amount + ", transactionStatus=" + transactionStatus + "]";
	}
<<<<<<< HEAD
    
}

=======

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
>>>>>>> origin/customer
=======

    @Enumerated(EnumType.STRING)	//to specify how an enum type should be persisted in the database

    private TransactionStatus transactionStatus;
 
    // Getters and Setters

    public Long getTransactionId() {

        return transactionId;

    }
 
    public void setTransactionId(Long transactionId) {

        this.transactionId = transactionId;

    }
 
    public Customers getCustomer() {

        return customer;

    }
 
    public void setCustomer(Customers customer) {

        this.customer = customer;

    }
 
    public Pets getPet() {

        return pet;

    }
 
    public void setPet(Pets pet) {

        this.pet = pet;

    }
 
    public Date getTransactionDate() {

        return transactionDate;

    }
 
    public void setTransactionDate(Date transactionDate) {

        this.transactionDate = transactionDate;

    }
 
    public double getAmount() {

        return amount;

    }
 
    public void setAmount(double amount) {

        this.amount = amount;

    }
 
    public TransactionStatus getTransactionStatus() {

        return transactionStatus;

    }
 
    public void setTransactionStatus(TransactionStatus transactionStatus) {

        this.transactionStatus = transactionStatus;

    }
 
	@Override

	public String toString() {

		return "Transactions [transactionId=" + transactionId + ", pet=" + pet + ", transactionDate=" + transactionDate

				+ ", amount=" + amount + ", transactionStatus=" + transactionStatus + "]";

	}

}
 
 
>>>>>>> origin/pets
