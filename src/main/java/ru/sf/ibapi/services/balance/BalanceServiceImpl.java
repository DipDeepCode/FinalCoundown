package ru.sf.ibapi.services.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sf.ibapi.entities.Balance;
import ru.sf.ibapi.entities.Customer;
import ru.sf.ibapi.entities.Operation;
import ru.sf.ibapi.entities.fabrics.BalanceFabric;
import ru.sf.ibapi.exceptions.ChangeBalanceException;
import ru.sf.ibapi.repositories.BalanceRepository;
import ru.sf.ibapi.services.balansehandler.BalanceHandler;
import ru.sf.ibapi.services.customer.CustomerService;
import ru.sf.ibapi.services.operation.OperationService;
import ru.sf.ibapi.services.transfer.TransferService;

import static ru.sf.ibapi.operationcodes.OperationCode.*;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceHandler balanceHandler;
    private final BalanceRepository balanceRepository;
    private final BalanceFabric balanceFabric;
    private final CustomerService customerService;
    private final TransferService transferService;
    private final OperationService operationService;

    @Autowired
    public BalanceServiceImpl(BalanceHandler balanceHandler,
                              BalanceRepository balanceRepository,
                              BalanceFabric balanceFabric,
                              @Lazy CustomerService customerService,
                              @Lazy TransferService transferService,
                              @Lazy OperationService operationService) {
        this.balanceHandler = balanceHandler;
        this.balanceRepository = balanceRepository;
        this.balanceFabric = balanceFabric;
        this.customerService = customerService;
        this.transferService = transferService;
        this.operationService = operationService;
    }

    @Override
    public Balance getBalance(Long customerId) {
        Customer customer = customerService.findCustomer(customerId);
        return customer.getBalance();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void putMoney(Long customerId, Long amount) throws ChangeBalanceException {
        Customer customer = customerService.findCustomer(customerId);
        Balance balance = customer.getBalance();
        balance.setBalance(balanceHandler.putMoney(balance.getBalance(), amount));
        balanceRepository.save(balance);
        operationService.addOperation(PUT_MONEY, amount, customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void takeMoney(Long customerId, Long amount) throws ChangeBalanceException {
        Customer customer = customerService.findCustomer(customerId);
        Balance balance = customer.getBalance();
        balance.setBalance(balanceHandler.takeMoney(balance.getBalance(), amount));
        balanceRepository.save(balance);
        operationService.addOperation(TAKE_MONEY, amount, customer);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void transferMoney(Long senderCustomerId, Long recipientCustomerId, Long amount) throws ChangeBalanceException {
        Customer sender = customerService.findCustomer(senderCustomerId);
        Balance senderBalance = sender.getBalance();
        senderBalance.setBalance(balanceHandler.takeMoney(senderBalance.getBalance(), amount));
        Operation senderOperation = operationService.addOperation(TRANSFER_MONEY_SEND, amount, sender);

        Customer recipient = customerService.findCustomer(recipientCustomerId);
        Balance recipientBalance = recipient.getBalance();
        recipientBalance.setBalance(balanceHandler.putMoney(recipientBalance.getBalance(), amount));
        Operation recipientOperation = operationService.addOperation(TRANSFER_MONEY_RECEIVE, amount, recipient);

        transferService.addTransfer(senderOperation, recipientOperation);
    }

    public void attachCustomerToBlancBalance(Customer customer) {
        Balance balance = balanceFabric.getBlancBalance();
        balance.setCustomer(customer);
        balanceRepository.save(balance);
    }

}
