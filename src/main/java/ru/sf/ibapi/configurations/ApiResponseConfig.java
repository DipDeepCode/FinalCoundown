package ru.sf.ibapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sf.ibapi.apiresponses.responsebuilder.ApiResponseBuilderImpl;

@Configuration
public class ApiResponseConfig {

    @Bean
    public ApiResponseBuilderImpl getBuilder() {
        return new ApiResponseBuilderImpl();
    }
}
