package com.isp.pet.configuration;

import com.isp.pet.utils.Constants;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> log.info(String.format(Constants.FORMAT_PRINT_REQUEST_RESPONSE, "REQUEST", requestTemplate.toString()));
    }
}
