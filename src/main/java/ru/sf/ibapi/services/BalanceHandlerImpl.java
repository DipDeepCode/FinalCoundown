package ru.sf.ibapi.services;

import lombok.AllArgsConstructor;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.BalanceException;

@AllArgsConstructor
public class BalanceHandlerImpl implements BalanceHandler {
    private Long maxBalanceValue;
    private Long minBalanceValue;

    @Override
    public Long putMoney(Customer customer, Long amount) throws BalanceException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance + amount;
        if (futureBalance > maxBalanceValue) {
            throw new BalanceException("Невозможно добавить заявленную сумму: превышен максимальный лимит");
        }
        return futureBalance;
    }

    @Override
    public Long takeMoney(Customer customer, Long amount) throws BalanceException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance - amount;
        if (futureBalance < minBalanceValue) {
            throw new BalanceException("Невозможно снять заявленную сумму: недостаточно средств на счете");
        }
        return futureBalance;
    }
}

