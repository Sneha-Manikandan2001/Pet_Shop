package com.service;

import com.dao.TransactionDAO;
import com.model.Customers;
import com.model.Pets;
import com.model.TransactionStatus;
import com.model.Transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private TransactionService transactionService;

    private Transactions transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transactions();
        transaction.setTransactionId(1L);
        transaction.setCustomer(new Customers());
        transaction.setPet(new Pets());
        transaction.setTransactionDate(new Date());
        transaction.setAmount(100.0);
        transaction.setTransactionStatus(TransactionStatus.Success);
    }

    @Test
    void testGetAllTransactions() {
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionDAO.findAll()).thenReturn(transactionsList);

        List<Transactions> result = transactionService.getAllTransactions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionDAO, times(1)).findAll();
    }

    @Test
    void testGetTransactionById() {
        when(transactionDAO.findById(1L)).thenReturn(Optional.of(transaction));

        Transactions result = transactionService.getTransactionById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        verify(transactionDAO, times(1)).findById(1L);
    }

    @Test
    void testGetTransactionsByCustomerId() {
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionDAO.findByCustomerId(1L)).thenReturn(transactionsList);

        List<Transactions> result = transactionService.getTransactionsByCustomerId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionDAO, times(1)).findByCustomerId(1L);
    }

    @Test
    void testGetTransactionsByStatus() {
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionDAO.findByTransactionStatus(TransactionStatus.Success)).thenReturn(transactionsList);

        List<Transactions> result = transactionService.getTransactionsByStatus(TransactionStatus.Success);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionDAO, times(1)).findByTransactionStatus(TransactionStatus.Success);
    }

    @Test
    void testAddTransaction() {
        when(transactionDAO.save(any(Transactions.class))).thenReturn(transaction);

        Transactions result = transactionService.addTransaction(transaction);
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        verify(transactionDAO, times(1)).save(any(Transactions.class));
    }

    @Test
    void testUpdateTransaction() {
        when(transactionDAO.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionDAO.save(any(Transactions.class))).thenReturn(transaction);

        Transactions result = transactionService.updateTransaction(1L, transaction);
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        verify(transactionDAO, times(1)).findById(1L);
        verify(transactionDAO, times(1)).save(any(Transactions.class));
    }
}