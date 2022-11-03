package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.ibapi.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}