package com.luckytree.shop.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    /**
     *  feign logging level setting
     */
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }

}
