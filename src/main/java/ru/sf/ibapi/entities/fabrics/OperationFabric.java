package ru.sf.ibapi.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.sf.ibapi.entities.Operation;

import java.time.ZonedDateTime;

@Component
public class OperationFabric {

    public Operation getBlancOperations() {
        Operation operation = new Operation();
        operation.setOperationTimestamp(ZonedDateTime.now()); //TODO переделать заполнение поля timestamp в функцию БД
        operation.setAmount(0L);
        return operation;
    }
}
