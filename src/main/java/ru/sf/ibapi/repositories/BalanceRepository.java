package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.ibapi.entities.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Balance findBalanceByCustomerId(Long customerId);
}