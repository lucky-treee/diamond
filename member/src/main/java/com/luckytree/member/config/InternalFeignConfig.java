package com.luckytree.member.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalFeignConfig {

    /**
     * feign logging level setting
     * @return
     */
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
