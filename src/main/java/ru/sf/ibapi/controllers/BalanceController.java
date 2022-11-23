package ru.sf.ibapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responses.ApiResponseCode;
import ru.sf.ibapi.custommapper.CustomMapper;
import ru.sf.ibapi.dto.BalanceDto;
import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.services.balance.BalanceService;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final BalanceService balanceService;
    private final ApiResponseBuilder apiResponseBuilder;
    private final CustomMapper customMapper;

    @Autowired
    public BalanceController(BalanceService balanceService,
                             ApiResponseBuilder apiResponseBuilder,
                             CustomMapper customMapper) {
        this.balanceService = balanceService;
        this.apiResponseBuilder = apiResponseBuilder;
        this.customMapper = customMapper;
    }

    @GetMapping("/get")
    @Operation(summary = "Получение текущего баланса пользователя",
            description = "Возвращает баланс пользователя по его customerId")
    public BalanceDto getBalance(@RequestParam Long customerId) {
        Balance balance = balanceService.getBalance(customerId);
        return customMapper.map(balance, BalanceDto.class);
    }

    @PutMapping("/put")
    @Operation(summary = "Пополнение баланса пользователя на заданную сумму",
            description = "Увеличивает значение баланса пользователя по его customerId на заданную сумму amount")
    public ApiResponse putMoney(@RequestParam Long customerId,
                                @RequestParam Long amount) throws ChangeBalanceException {
        balanceService.putMoney(customerId, amount);
        return apiResponseBuilder.buildSuccessfulResponse(ApiResponseCode.CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/take")
    @Operation(summary = "Снятие заданной суммы с баланса пользователя",
            description = "Уменьшает значение баланса пользователя по его customerId на заданную сумму amount")
    public ApiResponse takeMoney(@RequestParam Long customerId,
                                 @RequestParam Long amount) throws ChangeBalanceException {
        balanceService.takeMoney(customerId, amount);
        return apiResponseBuilder.buildSuccessfulResponse(ApiResponseCode.CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/transfer")
    @Operation(summary = "Перевод заданной суммы от одного пользователя другому",
            description = "Переводит заданную сумму amount от одного пользователя senderCustomerId другому recipientCustomerId")
    public ApiResponse addTransfer(@RequestParam Long senderCustomerId,
                                   @RequestParam Long recipientCustomerId,
                                   @RequestParam Long amount) throws ChangeBalanceException {
        balanceService.transferMoney(senderCustomerId, recipientCustomerId, amount);
        return apiResponseBuilder.buildSuccessfulResponse(ApiResponseCode.TRANSFER_SUCCESSFUL);
    }
}
