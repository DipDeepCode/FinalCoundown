package ru.sf.ibapi.services.balance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_ERROR;

class BalanceHandlerImplTest {
    private Customer customer;

    private BalanceHandler balanceHandler;

    @BeforeEach
    void setUp() {
        Long maxBalanceValue = 100L;
        Long minBalanceValue = -100L;
        balanceHandler = new BalanceHandlerImpl(maxBalanceValue, minBalanceValue);
        customer = new Customer();
    }

    @Test
    void putMoney_when_balanceLessMaxValue_expect_changeBalance() throws ChangeBalanceException {
        Long currentBalance = 0L;
        customer.setBalance(currentBalance);
        Long amountToPut = 100L;
        Long expectedBalance = currentBalance + amountToPut;
        balanceHandler.putMoney(customer, amountToPut);
        Long actualBalance = customer.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void putMoney_when_balanceExceedsMaxValue_expect_throwException() {
        Long currentBalance = 0L;
        customer.setBalance(currentBalance);
        Long amountToPut = 101L;
        String expectedMessage = "Превышен максимальный лимит";
        ApiResponseCodes expectedCode = CHANGE_BALANCE_ERROR;
        ChangeBalanceException thrownException = assertThrows(ChangeBalanceException.class,
                () -> balanceHandler.putMoney(customer, amountToPut));
        assertEquals(expectedMessage, thrownException.getMessage());
        assertEquals(expectedCode.getCode(), thrownException.getCode().getCode());
    }

    @Test
    void takeMoney_when_balanceMoreMinValue_expect_changeBalance() throws ChangeBalanceException {
        Long currentBalance = 0L;
        customer.setBalance(currentBalance);
        Long amountToTake = 100L;
        Long expectedBalance = currentBalance - amountToTake;
        balanceHandler.takeMoney(customer, amountToTake);
        Long actualBalance = customer.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void takeMoney_when_balanceExceedsMinValue_expect_thrownException() {
        Long currentBalance = 0L;
        customer.setBalance(currentBalance);
        Long amountToTake = 101L;
        String expectedMessage = "Недостаточно средств на счете";
        ApiResponseCodes expectedCode = CHANGE_BALANCE_ERROR;
        ChangeBalanceException thrownException = assertThrows(ChangeBalanceException.class,
                () -> balanceHandler.takeMoney(customer, amountToTake));
        assertEquals(expectedMessage, thrownException.getMessage());
        assertEquals(expectedCode.getCode(), thrownException.getCode().getCode());
    }
}