package ru.sf.ibapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sf.ibapi.services.balance.BalanceHandler;
import ru.sf.ibapi.services.balance.BalanceHandlerImpl;

@Configuration
public class BalanceHandlerConfig {

    @Bean
    public BalanceHandler getHandler() {
        long maxBalanceValue = 1_000_000L;
        long minBalanceValue = 0;
        return new BalanceHandlerImpl(maxBalanceValue, minBalanceValue);
    }
}
