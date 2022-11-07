package ru.sf.ibapi.services.customer;

import org.springframework.stereotype.Service;
import ru.sf.ibapi.exceptions.ChangeBalanceException;

@Service
public interface CustomerService {

    Long getBalance(Long id);
    void putMoney(Long id, Long amount) throws ChangeBalanceException;
    void takeMoney(Long id, Long amount) throws ChangeBalanceException;
}
