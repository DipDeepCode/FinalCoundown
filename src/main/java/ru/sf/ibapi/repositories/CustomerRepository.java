package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.ibapi.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdAndDisabledTimestampIsNull(Long customerId);
}