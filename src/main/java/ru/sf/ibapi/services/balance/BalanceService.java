package ru.sf.ibapi.services.balance;

import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

public interface BalanceService {
    Balance getBalance(Long customerId);
    void putMoney(Long customerId, Long amount) throws ChangeBalanceException;
    void takeMoney(Long customerId, Long amount) throws ChangeBalanceException;
    void transferMoney(Long senderCustomerId, Long recipientCustomerId, Long amount) throws ChangeBalanceException;
    void attachCustomerToBlancBalance(Customer customer);
}
