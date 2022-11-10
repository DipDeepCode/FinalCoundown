package ru.sf.ibapi.services.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.repositories.CustomerRepository;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_ERROR;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final CustomerRepository customerRepository;
    private static final Long MIN_BALANCE_VALUE = 0L;
    private static final Long MAX_BALANCE_VALUE = 1_000_000L;

    @Override
    public Long getBalance(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Balance balance = customer.getBalance();
        return balance.getBalance();
    }

    @Transactional
    @Override
    public void putMoney(Long customerId, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Balance balance = customer.getBalance();
        Long currentBalance = balance.getBalance();
        Long futureBalance = currentBalance + amount;
        if (isBalanceInLimits(futureBalance)) {
            throw new ChangeBalanceException("Превышен максимальный лимит", CHANGE_BALANCE_ERROR);
        }
        balance.setBalance(futureBalance);
    }

    @Transactional
    @Override
    public void takeMoney(Long customerId, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Balance balance = customer.getBalance();
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
