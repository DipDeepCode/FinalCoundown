package ru.sf.ibapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.apiresponses.ApiResponse;
import ru.sf.ibapi.apiresponses.ApiResponseBuilder;
import static ru.sf.ibapi.apiresponses.ApiResponseCodes.CHANGE_BALANCE_SUCCESSFUL;

import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.services.customer.CustomerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final ApiResponseBuilder apiResponseBuilder;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.add(customerDto), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<CustomerDto> find(@RequestParam Long id) {
        return new ResponseEntity<>(customerService.find(id), HttpStatus.OK);
    }

    @PutMapping("/updatename")
    public ResponseEntity<CustomerDto> updateName(@RequestParam Long id, String firstName, String lastName) {
        return new ResponseEntity<>(customerService.updateName(id, firstName, lastName), HttpStatus.OK);
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
    public ApiResponse putMoney(@RequestParam Long id, Long amount) throws ChangeBalanceException {
        customerService.putMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }

    @PutMapping("/balance/take")
    public ApiResponse takeMoney(@RequestParam Long id, Long amount) throws ChangeBalanceException {
        customerService.takeMoney(id, amount);
        return apiResponseBuilder.buildSuccessfulResponse(CHANGE_BALANCE_SUCCESSFUL);
    }
}
