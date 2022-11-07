package ru.sf.ibapi.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void setGetId() {
        Long expectedId = 1L;
        customer.setId(expectedId);
        Long actualId = customer.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    void setGetBalance() {
        Long expectedBalance = 100L;
        customer.setBalance(expectedBalance);
        Long actualBalance = customer.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }
}