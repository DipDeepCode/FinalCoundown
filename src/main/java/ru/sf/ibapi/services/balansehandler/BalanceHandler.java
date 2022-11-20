package ru.sf.ibapi.services.balansehandler;

import ru.sf.ibapi.exceptions.ChangeBalanceException;

public interface BalanceHandler {

    Long putMoney(Long balance, Long amount) throws ChangeBalanceException;
    Long takeMoney(Long balance, Long amount) throws ChangeBalanceException;
}
