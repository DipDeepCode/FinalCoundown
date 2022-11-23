package ru.sf.ibapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sf.ibapi.custommapper.CustomMapper;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.services.customer.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController { //TODO добавить логгирование запросов
    private final CustomerService customerService;
    private final CustomMapper customMapper;

    @Autowired
    public CustomerController(CustomerService customerService,
                              CustomMapper customMapper) {
        this.customerService = customerService;
        this.customMapper = customMapper;
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление пользователя",
            description = "Добавляет пользователя в БД, закрепляет за ним баланс с нулевым значением " +
                    "и в случае успеха возвращает dto пользователя в формате json")
    public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = customMapper.map(customerDto, Customer.class);
        customer = customerService.saveCustomer(customer);
        return customMapper.map(customer, CustomerDto.class);
    }

    @GetMapping("/find")
    @Operation(summary = "Поиск пользователя по id",
            description = "Производит поиск пользователя и в случае успеха возвращает dto пользователя в формате json")
    public CustomerDto findCustomer(@RequestParam Long customerId) {
        Customer customer = customerService.findCustomer(customerId);
        return customMapper.map(customer, CustomerDto.class);
    }

    @PutMapping("/updatenames")
    @Operation(summary = "Изменение имени и фамилии пользователя по id",
            description = "Производит изменение полей firstname и lastname у пользователя на переданные. Оба значения обязательны." +
                    "В случае успеха возвращает dto пользователя в формате json")
    public CustomerDto updateCustomerNames(@RequestParam Long customerId,
                                           @RequestParam String firstname,
                                           @RequestParam String lastname) {
        Customer customer = customerService.updateCustomerNames(customerId, firstname, lastname);
        return customMapper.map(customer, CustomerDto.class);
    }

    @DeleteMapping("delete")
    @Operation(summary = "Отключение пользователя по id",
            description = "Не удаляет пользователя из БД. Устанавливает у него в поле disabledTimestamp текущее значение. " +
                    "Восстановление из этого состояния не предусмотрено")
    public void deleteCustomer(@RequestParam Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}

