package ru.sf.ibapi.services.customer;

import ru.sf.ibapi.entities.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer findCustomer(Long id);
    void deleteCustomer(Long customerId);
    Customer updateCustomerNames(Long customerId, String firstname, String lastname);
}
