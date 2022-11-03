package ru.sf.ibapi.services;

import org.springframework.stereotype.Service;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.exceptions.BalanceException;

@Service
public interface CustomerService {

    CustomerDto add(CustomerDto customerDto);
    CustomerDto find(Long id);
    CustomerDto updateName(CustomerDto customerDto);
    void delete(Long id);
    Long getBalance(Long id);
    Long putMoney(Long id, Long amount) throws BalanceException;
    Long takeMoney(Long id, Long amount) throws BalanceException;
}
