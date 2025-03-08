package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;
 
public class TransactionsTest {
 
    @Test
    public void testTransactionsConstructorAndGetters() {
        Customers customer = new Customers();
        Pets pet = new Pets();
        Date date = new Date();
        Transactions transaction = new Transactions();
        transaction.setCustomer(customer);
        transaction.setPet(pet);
        transaction.setTransactionDate(date);
        transaction.setAmount(100.0);
        transaction.setTransactionStatus(TransactionStatus.Success);
 
        assertEquals(customer, transaction.getCustomer());
        assertEquals(pet, transaction.getPet());
        assertEquals(date, transaction.getTransactionDate());
        assertEquals(100.0, transaction.getAmount());
        assertEquals(TransactionStatus.Success, transaction.getTransactionStatus());
    }
}
 
