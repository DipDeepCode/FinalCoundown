package ru.sf.ibapi.services.balance;

import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

public interface BalanceHandler {

    void putMoney(Customer customer, Long amount) throws ChangeBalanceException;
    void takeMoney(Customer customer, Long amount) throws ChangeBalanceException;
}
