package com.controller;

import com.model.Customers;
import com.model.Pets;
import com.model.TransactionStatus;
import com.model.Transactions;
import com.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

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
        when(transactionService.getAllTransactions()).thenReturn(transactionsList);

        List<Transactions> result = transactionController.getAllTransactions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void testGetTransactionById() {
        when(transactionService.getTransactionById(1L)).thenReturn(transaction);

        Transactions result = transactionController.getTransactionById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getTransactionId());
        verify(transactionService, times(1)).getTransactionById(1L);
    }

    @Test
    void testGetTransactionsByCustomerId() {
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionService.getTransactionsByCustomerId(1L)).thenReturn(transactionsList);

        List<Transactions> result = transactionController.getTransactionsByCustomerId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionService, times(1)).getTransactionsByCustomerId(1L);
    }

    @Test
    void testGetSuccessfulTransactions() {
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionService.getTransactionsByStatus(TransactionStatus.Success)).thenReturn(transactionsList);

        List<Transactions> result = transactionController.getSuccessfulTransactions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionService, times(1)).getTransactionsByStatus(TransactionStatus.Success);
    }

    @Test
    void testGetFailedTransactions() {
        transaction.setTransactionStatus(TransactionStatus.Failed);
        List<Transactions> transactionsList = Arrays.asList(transaction);
        when(transactionService.getTransactionsByStatus(TransactionStatus.Failed)).thenReturn(transactionsList);

        List<Transactions> result = transactionController.getFailedTransactions();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(transactionService, times(1)).getTransactionsByStatus(TransactionStatus.Failed);
    }

    @Test
    void testAddTransaction() {
        when(transactionService.addTransaction(any(Transactions.class))).thenReturn(transaction);

        ResponseEntity<Map<String, Object>> response = transactionController.addTransaction(transaction);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        verify(transactionService, times(1)).addTransaction(any(Transactions.class));
    }

    @Test
    void testUpdateTransaction() {
        when(transactionService.updateTransaction(eq(1L), any(Transactions.class))).thenReturn(transaction);

        ResponseEntity<Map<String, Object>> response = transactionController.updateTransaction(1L, transaction);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(transactionService, times(1)).updateTransaction(eq(1L), any(Transactions.class));
    }
}