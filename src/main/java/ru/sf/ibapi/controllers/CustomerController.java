package ru.sf.ibapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_SUCCESSFUL;

import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.services.customer.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ApiResponseBuilder apiResponseBuilder;

    @GetMapping("/balance/get")
    @Operation(summary = "Get balance", description = "Получить текущий баланс пользователя")
    public ApiResponse getBalance(@RequestParam Long id) {
        long balance = customerService.getBalance(id);
        return apiResponseBuilder.buildBalanceResponse(balance);
    }

    @PutMapping("/balance/put")
    @Operation(summary = "Put money", description = "Пополнение баланса пользователя на заданную сумму")
    public ApiResponse putMoney(@RequestParam Long id,
                                @RequestParam Long amount) throws ChangeBalanceException {
        customerService.putMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/balance/take")
    @Operation(summary = "Take money", description = "Снятие заданной суммы с баланса пользователя")
    public ApiResponse takeMoney(@RequestParam Long id,
                                 @RequestParam Long amount) throws ChangeBalanceException {
        customerService.takeMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }
}
