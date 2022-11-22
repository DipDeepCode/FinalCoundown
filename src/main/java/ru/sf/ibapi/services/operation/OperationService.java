package ru.sf.ibapi.services.operation;

import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.entities.Operation;
import ru.sf.ibapi.operationcodes.OperationCode;

import java.time.ZonedDateTime;
import java.util.List;

public interface OperationService {
    List<Operation> getOperationList(Long customerId, ZonedDateTime beginningOfRange, ZonedDateTime endOfRange);
    List<Operation> getOperationList(Long customerId);
    Operation addOperation(OperationCode code, Long amount, Customer customer);
}
