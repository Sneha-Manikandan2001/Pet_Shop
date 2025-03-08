package com.dao;

import com.model.Transactions;
import com.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transactions, Long> {

    // Find transactions by customer ID
    @Query("SELECT t FROM Transactions t WHERE t.customer.id = :customerId")
    List<Transactions> findByCustomerId(@Param("customerId") Long customerId);

    // Find transactions by transaction status
    @Query("SELECT t FROM Transactions t WHERE t.transactionStatus = :status")
    List<Transactions> findByTransactionStatus(@Param("status") TransactionStatus status);
}
