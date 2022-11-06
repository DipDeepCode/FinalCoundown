package ru.sf.ibapi.services.balance;

import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ApiException;

public interface BalanceHandler {

    Long putMoney(Customer customer, Long amount) throws ApiException;
    Long takeMoney(Customer customer, Long amount) throws ApiException;
}
