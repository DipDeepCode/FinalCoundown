package ru.sf.ibapi.services.customer;

import org.springframework.stereotype.Service;
import ru.sf.ibapi.dto.CustomerDto;
import ru.sf.ibapi.exceptions.ApiException;

@Service
public interface CustomerService {

    CustomerDto add(CustomerDto customerDto);
    CustomerDto find(Long id);
    CustomerDto updateName(Long id, String firstName, String lastName);
    void delete(Long id);
    Long getBalance(Long id);
    void putMoney(Long id, Long amount) throws ApiException;
    void takeMoney(Long id, Long amount) throws ApiException;
}
