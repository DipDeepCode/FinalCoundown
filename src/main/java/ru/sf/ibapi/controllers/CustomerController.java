package ru.sf.ibapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.apiresponses.responses.ApiResponse;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilder;
import static ru.sf.ibapi.apiresponses.responsecodes.ApiResponseCodes.CHANGE_BALANCE_SUCCESSFUL;

import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.exceptions.ApiException;
import ru.sf.ibapi.services.customer.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ApiResponseBuilder apiResponseBuilder;

    @PostMapping("/add")
    public CustomerDto add(@RequestBody CustomerDto customerDto) {
        return customerService.add(customerDto);
    }

    @GetMapping("/find")
    public CustomerDto find(@RequestParam Long id) {
        return customerService.find(id);
    }

    @PutMapping("/updatename")
    public CustomerDto updateName(@RequestParam Long id, String firstName, String lastName) {
        return customerService.updateName(id, firstName, lastName);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        customerService.delete(id);
    }

    @GetMapping("/balance/get")
    public ApiResponse getBalance(@RequestParam Long id) {
        long balance = customerService.getBalance(id);
        return apiResponseBuilder.buildBalanceResponse(balance);
    }

    @PutMapping("/balance/put")
    public ApiResponse putMoney(@RequestParam Long id, Long amount) throws ApiException {
        customerService.putMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/balance/take")
    public ApiResponse takeMoney(@RequestParam Long id, Long amount) throws ApiException {
        customerService.takeMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }
}
