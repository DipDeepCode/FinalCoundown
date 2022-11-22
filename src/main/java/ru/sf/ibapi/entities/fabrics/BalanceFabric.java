package ru.sf.ibapi.entities.fabrics;

import org.springframework.stereotype.Component;
import ru.sf.ibapi.entities.Balance;

@Component
public class BalanceFabric {

    public Balance getBlancBalance() {
        Balance balance = new Balance();
        balance.setBalance(0L);
        return balance;
    }
}
