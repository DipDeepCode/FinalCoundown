package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sf.ibapi.entities.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}