package ru.sf.ibapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.services.customer.CustomerService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    @Operation(summary = "Add customer", description = "Добавить пользователя")
    public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.add(customerDto);
    }

    @PutMapping("/update")
    @Operation(summary = "Update customer", description = "Изменение имени и фамилии пользователя по id")
    public CustomerDto updateCustomer(@RequestParam Long id,
                                      @RequestParam String firstname,
                                      @RequestParam String lastname) {
        return customerService.update(id, firstname, lastname);
    }

    @GetMapping("/find")
    @Operation(summary = "Find customer", description = "Поиск пользователя по id")
    public CustomerDto findCustomer(@RequestParam Long id) {
        return customerService.find(id);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete customer", description = "Удаление пользователя по id")
    public void deleteCustomer(Long id) {
        customerService.delete(id);
    }
}
