package ru.sf.ibapi.services.customer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.entities.balancefabric.BalanceFabric;
import ru.sf.ibapi.repositories.CustomerRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final BalanceFabric balanceFabric;

    @Override
    public CustomerDto add(CustomerDto customerDto) {
        Customer customer = dtoToEntity(customerDto);
        customer.setBalance(balanceFabric.getBlancBalance());
        customer = customerRepository.save(customer);
        return entityToDto(customer);
    }

    @Transactional
    @Override
    public CustomerDto update(Long id, String firstname, String lastname) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        return entityToDto(customer);
    }

    @Override
    public CustomerDto find(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        return entityToDto(customer);
    }

    @Override
    public void delete(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Пользователь не найден");
        }
    }

    private CustomerDto entityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer dtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
