package ru.sf.ibapi.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.sf.ibapi.entities.Transfer;

@Component
public class TransferFabric {

    public Transfer getBlancTransfer() {
        return new Transfer();
    }
}
