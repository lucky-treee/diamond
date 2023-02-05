package com.luckytree.member_service.config;

import feign.Logger;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    /**
     * feign logging level setting
     * @return
     */
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
