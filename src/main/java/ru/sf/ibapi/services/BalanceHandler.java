package ru.sf.ibapi.services;

import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.BalanceException;

public interface BalanceHandler {

    Long putMoney(Customer customer, Long amount) throws BalanceException;
    Long takeMoney(Customer customer, Long amount) throws BalanceException;
}
