package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sf.ibapi.entities.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}