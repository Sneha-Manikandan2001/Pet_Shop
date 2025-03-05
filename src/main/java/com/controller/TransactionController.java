package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.TransactionStatus;
import com.model.Transactions;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transaction_history")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transactions> getAllTransactions() {
        List<Transactions> transactions = transactionService.getAllTransactions();
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found");
        }
        return transactions;
    }

    @GetMapping("/{transactionId}")
    public Transactions getTransactionById(@PathVariable Long transactionId) {
        Transactions transaction = transactionService.getTransactionById(transactionId);
        if (transaction == null) {
            throw new ResourceNotFoundException("Transaction not found");
        }
        return transaction;
    }

    @GetMapping("/by_customer/{customerId}")
    public List<Transactions> getTransactionsByCustomerId(@PathVariable Long customerId) {
        List<Transactions> transactions = transactionService.getTransactionsByCustomerId(customerId);
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for customer");
        }
        return transactions;
    }

    @GetMapping("/successful")
    public List<Transactions> getSuccessfulTransactions() {
        List<Transactions> transactions = transactionService.getTransactionsByStatus(TransactionStatus.Success);
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No successful transactions found");
        }
        return transactions;
    }

    @GetMapping("/failed")
    public List<Transactions> getFailedTransactions() {
        List<Transactions> transactions = transactionService.getTransactionsByStatus(TransactionStatus.Failed);
        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No failed transactions found");
        }
        return transactions;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addTransaction(@RequestBody Transactions transaction) {
        if (transaction == null) {
        	throw new RuntimeException("Invalid");
        }
        transactionService.addTransaction(transaction);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Transaction added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{transactionId}")
    public ResponseEntity<Map<String, Object>> updateTransaction(@PathVariable Long transactionId, @RequestBody Transactions transaction) {
        if (transaction == null) {
        	throw new RuntimeException("Invalid");
        }
        transactionService.updateTransaction(transactionId, transaction);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", new Date());
        response.put("message", "Transaction updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}