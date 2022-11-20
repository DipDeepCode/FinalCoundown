package ru.sf.ibapi.controllers;

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
    public CustomerDto addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = customMapper.map(customerDto, Customer.class);
        customer = customerService.saveCustomer(customer);
        return customMapper.map(customer, CustomerDto.class);
    }

    @GetMapping("/find")
    public CustomerDto findCustomer(@RequestParam Long customerId) {
        Customer customer = customerService.findCustomer(customerId);
        return customMapper.map(customer, CustomerDto.class);
    }

    @PutMapping("/updatenames")
    public CustomerDto updateCustomerNames(@RequestParam Long customerId,
                                           @RequestParam String firstname,
                                           @RequestParam String lastname) {
        Customer customer = customerService.updateCustomerNames(customerId, firstname, lastname);
        return customMapper.map(customer, CustomerDto.class);
    }

    @DeleteMapping("delete")
    public void deleteCustomer(@RequestParam Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}

