package ru.sf.ibapi.services.transfer;

import ru.sf.ibapi.entities.Operation;

public interface TransferService {

    void addTransfer(Operation senderOperation, Operation recipientOperation);
}
