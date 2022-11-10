package ru.sf.ibapi.entities.balancefabric;

import org.springframework.stereotype.Component;
import ru.sf.ibapi.entities.Balance;

@Component
public class BalanceFabricImpl implements BalanceFabric {

    @Override
    public Balance getBlancBalance() {
        Balance balance = new Balance();
        balance.setBalance(0L);
        return balance;
    }
}
