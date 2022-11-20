package ru.sf.ibapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sf.ibapi.services.balansehandler.BalanceLimitsChecker;
import ru.sf.ibapi.services.balansehandler.DefaultBalanceLimitsChecker;

@Configuration
public class BalanceLimitsConfig {
    private final static Long LOWER_LIMIT = 0L;
    private final static Long UPPER_LIMIT = 1000L;

    @Bean
    public BalanceLimitsChecker getBalanceLimitsChecker() {
        return new DefaultBalanceLimitsChecker(LOWER_LIMIT, UPPER_LIMIT);
    }
}
