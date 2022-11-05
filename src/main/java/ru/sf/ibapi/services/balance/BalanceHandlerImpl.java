package ru.sf.ibapi.services.balance;

import lombok.RequiredArgsConstructor;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

@RequiredArgsConstructor
public class BalanceHandlerImpl implements BalanceHandler {
    private final Long maxBalanceValue;
    private final Long minBalanceValue;

    @Override
    public Long putMoney(Customer customer, Long amount) throws ChangeBalanceException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance + amount;
        if (futureBalance > maxBalanceValue) {
            throw new ChangeBalanceException("Превышен максимальный лимит");
        }
        return futureBalance;
    }

    @Override
    public Long takeMoney(Customer customer, Long amount) throws ChangeBalanceException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance - amount;
        if (futureBalance < minBalanceValue) {
            throw new ChangeBalanceException("Недостаточно средств на счете");
        }
        return futureBalance;
    }
}

