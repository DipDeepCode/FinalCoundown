package ru.sf.ibapi.services.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sf.ibapi.entities.Operation;
import ru.sf.ibapi.entities.Transfer;
import ru.sf.ibapi.entities.fabrics.TransferFabric;
import ru.sf.ibapi.repositories.TransferRepository;

@Service
public class TransferServiceImpl implements TransferService {
    private final TransferFabric transferFabric;
    private final TransferRepository transferRepository;

    @Autowired
    public TransferServiceImpl(TransferFabric transferFabric,
                               TransferRepository transferRepository) {
        this.transferFabric = transferFabric;
        this.transferRepository = transferRepository;
    }

    @Override
    public void addTransfer(Operation senderOperation, Operation recipientOperation) {
        Transfer transfer = transferFabric.getBlancTransfer();
        transfer.setSenderOperation(senderOperation);
        transfer.setRecipientOperation(recipientOperation);
        transferRepository.save(transfer);
    }
}
