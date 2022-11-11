package ru.sf.ibapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.services.balance.BalanceService;

import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_SUCCESSFUL;

@RequiredArgsConstructor
@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;
    private final ApiResponseBuilder apiResponseBuilder;

    @GetMapping("/get")
    @Operation(summary = "Get balance", description = "Получить текущий баланс пользователя")
    public ApiResponse getBalance(@RequestParam Long customerId) {
        Long balance = balanceService.getBalance(customerId);
        return apiResponseBuilder.buildBalanceResponse(balance);
    }

    @PutMapping("/put")
    @Operation(summary = "Put money", description = "Пополнение баланса пользователя на заданную сумму")
    public ApiResponse putMoney(@RequestParam Long customerId,
                                @RequestParam Long amount) throws ChangeBalanceException {
        balanceService.putMoney(customerId, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/take")
    @Operation(summary = "Take money", description = "Снятие заданной суммы с баланса пользователя")
    public ApiResponse takeMoney(@RequestParam Long customerId,
                                 @RequestParam Long amount) throws ChangeBalanceException {
        balanceService.takeMoney(customerId, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }
}
