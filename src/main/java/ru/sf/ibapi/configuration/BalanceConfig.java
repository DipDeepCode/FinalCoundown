package ru.sf.ibapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sf.ibapi.entities.Balance;

@Configuration
public class BalanceConfig {

    @Bean
    @Scope("prototype")
    public Balance getBalance() {
        Balance balance = new Balance();
        balance.setBalance(0L);
        return balance;
    }
}
