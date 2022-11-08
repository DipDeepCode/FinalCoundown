package ru.sf.ibapi.services.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.repositories.BalanceRepository;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_ERROR;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository balanceRepository;
    private static final Long MIN_BALANCE_VALUE = 0L;
    private static final Long MAX_BALANCE_VALUE = 1_000_000L;

    @Override
    public Long getBalance(Long id) {
        Balance balance = balanceRepository.findBalanceByCustomerId(id);
        return balance.getBalance();
    }

    @Transactional
    @Override
    public void putMoney(Long id, Long amount) throws ChangeBalanceException {
        Balance balance = balanceRepository.findBalanceByCustomerId(id);
        Long currentBalance = balance.getBalance();
        Long futureBalance = currentBalance + amount;
        if (isBalanceInLimits(futureBalance)) {
            throw new ChangeBalanceException("Превышен максимальный лимит", CHANGE_BALANCE_ERROR);
        }
        balance.setBalance(futureBalance);
    }

    @Transactional
    @Override
    public void takeMoney(Long id, Long amount) throws ChangeBalanceException {
        Balance balance = balanceRepository.findBalanceByCustomerId(id);
        Long currentBalance = balance.getBalance();
        Long futureBalance = currentBalance - amount;
        if (isBalanceInLimits(futureBalance)) {
            throw new ChangeBalanceException("Недостаточно средств на счете", CHANGE_BALANCE_ERROR);
        }
        balance.setBalance(futureBalance);
    }

    private boolean isBalanceInLimits(Long value) {
        return value < MIN_BALANCE_VALUE || value > MAX_BALANCE_VALUE;
    }
}
