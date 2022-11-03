package ru.sf.ibapi.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.BalanceException;
import ru.sf.ibapi.repositories.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BalanceHandler balanceHandler;

    @Override
    public CustomerDto add(CustomerDto customerDto) {
        Customer customer = dtoToEntity(customerDto);
        return entityToDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDto find(Long id) {
        return entityToDto(customerRepository.findById(id).orElseThrow());
    }

    @Override
    public CustomerDto updateName(CustomerDto customerDto) {
        Customer customer = dtoToEntity(customerDto);
        return entityToDto(customerRepository.save(customer));
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Long getBalance(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return customer.getBalanceInRoubles();
    }

    @Override
    public Long putMoney(Long id, Long amount) throws BalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return balanceHandler.putMoney(customer, amount);
    }

    @Override
    public Long takeMoney(Long id, Long amount) throws BalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return balanceHandler.takeMoney(customer, amount);
    }

    private CustomerDto entityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer dtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
