package ru.sf.ibapi.services.customer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.repositories.CustomerRepository;
import ru.sf.ibapi.services.balance.BalanceHandler;

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
    public CustomerDto updateName(Long id, String firstName, String lastName) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
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

    @Transactional
    @Override
    public void putMoney(Long id, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        Long newBalance = balanceHandler.putMoney(customer, amount);
        customer.setBalanceInRoubles(newBalance);
    }

    @Transactional
    @Override
    public void takeMoney(Long id, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        Long newBalance = balanceHandler.takeMoney(customer, amount);
        customer.setBalanceInRoubles(newBalance);
    }

    private CustomerDto entityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer dtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
