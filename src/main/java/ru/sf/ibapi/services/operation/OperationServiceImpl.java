package ru.sf.ibapi.services.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.entities.Operation;
import ru.sf.ibapi.entities.fabrics.OperationFabric;
import ru.sf.ibapi.operationcodes.OperationCode;
import ru.sf.ibapi.repositories.OperationRepository;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationFabric operationFabric;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository,
                                OperationFabric operationFabric) {
        this.operationRepository = operationRepository;
        this.operationFabric = operationFabric;
    }

    @Override
    public List<Operation> getOperationList(Long customerId, ZonedDateTime operationTimestampStart, ZonedDateTime operationTimestampEnd) {
        List<Operation> operationList = operationRepository
                .findAllByCustomer_IdAndOperationTimestampBetween(customerId, operationTimestampStart, operationTimestampEnd);
        if (operationList.size() == 0) {
            throw new EntityNotFoundException("Нет данных за указанный период");//TODO в случае несуществующего id должно быть сообщение пользователь не найден
        }
        return operationList;
    }

    @Override
    public List<Operation> getOperationList(Long customerId) {
        List<Operation> operationList = operationRepository
                .findAllByCustomer_Id(customerId);
        if (operationList.size() == 0) {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        return operationList;
    }

    public Operation addOperation(OperationCode code, Long amount, Customer customer) {
        Operation operation = operationFabric.getBlancOperations();
        operation.setOperationCode(code);
        operation.setAmount(amount);
        operation.setCustomer(customer);
        return operationRepository.save(operation);
    }
}
