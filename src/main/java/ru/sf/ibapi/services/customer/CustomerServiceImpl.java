package ru.sf.ibapi.services.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.repositories.CustomerRepository;
import ru.sf.ibapi.services.balance.BalanceHandler;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BalanceHandler balanceHandler;

    @Override
    public Long getBalance(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return customer.getBalance();
    }

    @Transactional
    @Override
    public void putMoney(Long id, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        balanceHandler.putMoney(customer, amount);
    }

    @Transactional
    @Override
    public void takeMoney(Long id, Long amount) throws ChangeBalanceException {
        Customer customer = customerRepository.findById(id).orElseThrow();
        balanceHandler.takeMoney(customer, amount);
    }
}
