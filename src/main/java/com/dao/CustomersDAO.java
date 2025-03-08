package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.model.Customers;

public interface CustomersDAO extends JpaRepository<Customers, Long> {
    // Additional query methods can be defined here if needed
	// Custom query method to find by first name and last name
    Customers findByFirstNameAndLastName(String firstName, String lastName);
 // Custom query method to find by city
    @Query("SELECT c FROM Customers c WHERE c.address.city = :city")
    List<Customers> findByCity(String city);
    //custom query for finding customers by state
    @Query("SELECT c FROM Customers c WHERE c.address.state = :state")
    List<Customers> findByState(String state);
    
//custom Query for finding customers with transactions
    @Query("SELECT c FROM Customers c JOIN c.transactions t WHERE t.transactionStatus = :status")
    List<Customers> findCustomersByTransactionStatus(String status);

//custom query for finding customers without transactions
    @Query("SELECT c FROM Customers c WHERE c.transactions IS EMPTY")
    List<Customers> findCustomersWithoutTransactions();
}