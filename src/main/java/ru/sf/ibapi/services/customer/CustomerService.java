package ru.sf.ibapi.services.customer;

import ru.sf.ibapi.dto.CustomerDto;

public interface CustomerService {

    CustomerDto add(CustomerDto customerDto);

    CustomerDto update(Long id, String firstname, String lastname);

    CustomerDto find(Long id);

    void delete(Long id);
}
