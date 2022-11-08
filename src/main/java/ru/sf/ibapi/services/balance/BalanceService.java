package ru.sf.ibapi.services.balance;

import ru.sf.ibapi.exceptions.ChangeBalanceException;

public interface BalanceService {

    Long getBalance(Long id);

    void putMoney(Long id, Long amount) throws ChangeBalanceException;

    void takeMoney(Long id, Long amount) throws ChangeBalanceException;
}
