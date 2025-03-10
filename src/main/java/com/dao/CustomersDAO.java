package com.dao;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import com.model.Customers;
import com.model.Pets;
import com.model.TransactionStatus;
import com.model.Transactions;
 
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
////custom Query for finding customers with transactions
//    
//    @Query("SELECT c FROM Customers c JOIN c.transactions t WHERE t.transactionStatus = :status")
//    List<Customers> findCustomersByTransactionStatus(String status);
 
//custom query for finding customers without transactions
    @Query("SELECT c FROM Customers c WHERE c.transactions IS EMPTY")
    List<Customers> findCustomersWithoutTransactions();
//  //custom Query for finding transactions with TransactionStatus
//    
//    @Query("SELECT c FROM Customers c WHERE c.transactionStatus = :status")
//    List<Customers> findCustomersByTransactionStatus(TransactionStatus status);
// Custom query method to find customers by transaction status (String)
    @Query("SELECT c FROM Customers c JOIN c.transactions t WHERE t.transactionStatus =:status")
    List<Customers> findCustomersByTransactionStatus(@Param("status")TransactionStatus  status);
  //custom Query for finding transactions with CustomerId
        @Query("SELECT t FROM Transactions t WHERE t.customer.id = :customerId")
        List<Transactions> findTransactionsByCustomerId(@Param("customerId") Long customerId);
      //custom Query for finding pets with CustomerId
//        @Query("SELECT p FROM Pets p WHERE p.customer.id = :customerId")
//        List<Pets> findPetsByCustomerId(@Param("customerId") Long customerId);
//        @Query("SELECT t FROM Transactions t WHERE t.customer.id = :customerId")
//        List<Transactions> findTransactionsByCustomerId(@Param("customerId") Long customerId);
 
//        @Query("SELECT p FROM Pets p WHERE p.transaction.customer.id = :customerId")
////        List<Pets> findPetsByCustomerId(@Param("customerId") Long customerId);
//        @Query("SELECT p FROM Pets p " +
//                "JOIN p.transaction t " +
//                "JOIN t.customer c " +
//                "WHERE c.firstName = :firstName")
        @Query(nativeQuery=true,value="select c.first_name,t.transaction_id,p.name from customers c join transactions t on c.customer_id=t.customer_id join pets p on p.pet_id=t.pet_id where c.first_name=:firstName")
         Optional<List<Object>> findPetsByCustomerFirstName(@Param("firstName") String firstName);
    }
