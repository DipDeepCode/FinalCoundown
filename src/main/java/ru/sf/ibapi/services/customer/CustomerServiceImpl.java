package ru.sf.ibapi.services.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.repositories.CustomerRepository;
import ru.sf.ibapi.services.balance.BalanceService;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BalanceService balanceService;

    @Autowired
        public CustomerServiceImpl(CustomerRepository customerRepository,
                                   @Lazy BalanceService balanceService) {
        this.customerRepository = customerRepository;
        this.balanceService = balanceService;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setCreatedTimestamp(ZonedDateTime.now());
        customer = customerRepository.save(customer);
        balanceService.attachCustomerToBlancBalance(customer);
        return customer;
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerRepository.findByIdAndDisabledTimestampIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = findCustomer(customerId);
        customer.setDisabledTimestamp(ZonedDateTime.now());
        customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerNames(Long customerId, String firstname, String lastname) {
        Customer customer = findCustomer(customerId);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        return customerRepository.save(customer);
    }
}
