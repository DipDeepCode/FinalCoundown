package ru.sf.ibapi.services.balansehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

@Component
public class BalanceHandlerImpl implements BalanceHandler {
    private final BalanceLimitsChecker balanceLimitsChecker;

    @Autowired
    public BalanceHandlerImpl(BalanceLimitsChecker balanceLimitsChecker) {
        this.balanceLimitsChecker = balanceLimitsChecker;
    }

    @Override
    public Long putMoney(Long balance, Long amount) throws ChangeBalanceException {
        Long futureBalance = balance + amount;
        if (balanceLimitsChecker.isUpperLimitExceeded(futureBalance)) {
            throw new ChangeBalanceException("Превышен максимальный лимит средств на счете");
        } else {
            return futureBalance;
        }
    }

    @Override
    public Long takeMoney(Long balance, Long amount) throws ChangeBalanceException {
        Long futureBalance = balance - amount;
        if (balanceLimitsChecker.isLowerLimitExceeded(futureBalance)) {
            throw new ChangeBalanceException("Недостаточно средств на счете");
        } else {
            return futureBalance;
        }
    }
}
