package ru.sf.ibapi.services.customer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.repositories.BalanceRepository;
import ru.sf.ibapi.repositories.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BalanceRepository balanceRepository;
    private final Balance balance;

    @Override
    public CustomerDto add(CustomerDto customerDto) {
        Customer customer = dtoToEntity(customerDto);
        customer = customerRepository.save(customer);
        balance.setCustomer(customer);
        balanceRepository.save(balance);
        return entityToDto(customer);
    }

    @Transactional
    @Override
    public CustomerDto update(Long id, String firstname, String lastname) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        return entityToDto(customer);
    }

    @Override
    public CustomerDto find(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return entityToDto(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDto entityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer dtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
