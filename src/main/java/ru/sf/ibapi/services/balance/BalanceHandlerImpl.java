package ru.sf.ibapi.services.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ApiException;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_ERROR;

@RequiredArgsConstructor
public class BalanceHandlerImpl implements BalanceHandler {
    private final Long maxBalanceValue;
    private final Long minBalanceValue;

    @Override
    public Long putMoney(Customer customer, Long amount) throws ApiException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance + amount;
        if (futureBalance > maxBalanceValue) {
            throw new ApiException("Превышен максимальный лимит", CHANGE_BALANCE_ERROR, HttpStatus.CONFLICT);
        }
        return futureBalance;
    }

    @Override
    public Long takeMoney(Customer customer, Long amount) throws ApiException {
        Long currentBalance = customer.getBalanceInRoubles();
        long futureBalance = currentBalance - amount;
        if (futureBalance < minBalanceValue) {
            throw new ApiException("Недостаточно средств на счете", CHANGE_BALANCE_ERROR, HttpStatus.CONFLICT);
        }
        return futureBalance;
    }
}

