package ru.sf.ibapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sf.ibapi.entities.Operation;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findAllByCustomer_Id(Long customerId);
    List<Operation> findAllByCustomer_IdAndOperationTimestampBetween(Long id, ZonedDateTime beginningOfRange, ZonedDateTime endOfRange);
}