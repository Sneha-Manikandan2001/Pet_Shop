package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TransactionDAO;
import com.model.TransactionStatus;
import com.model.Transactions;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    public List<Transactions> getAllTransactions() {
        return transactionDAO.findAll();
    }

    public Transactions getTransactionById(Long transactionId) {
        return transactionDAO.findById(transactionId).orElse(null);
    }

    public List<Transactions> getTransactionsByCustomerId(Long customerId) {
        return transactionDAO.findByCustomerId(customerId);
    }

    public List<Transactions> getTransactionsByStatus(TransactionStatus transactionStatus) {
        return transactionDAO.findByTransactionStatus(transactionStatus);
    }

    public Transactions addTransaction(Transactions transaction) {
        return transactionDAO.save(transaction);
    }

    public Transactions updateTransaction(Long transactionId, Transactions transaction) {
        Transactions existingTransaction = transactionDAO.findById(transactionId).orElse(null);
        if (existingTransaction != null) {
            existingTransaction.setCustomer(transaction.getCustomer());
            existingTransaction.setPet(transaction.getPet());
            existingTransaction.setTransactionDate(transaction.getTransactionDate());
            existingTransaction.setAmount(transaction.getAmount());
            existingTransaction.setTransactionStatus(transaction.getTransactionStatus());
            return transactionDAO.save(existingTransaction);
        }
        return null;
    }
}