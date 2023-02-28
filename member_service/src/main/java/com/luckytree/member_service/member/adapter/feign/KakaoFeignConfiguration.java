package com.luckytree.member_service.member.adapter.feign;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Content-Type", "application/x-www-form-urlencoded");
    }

/*    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignException.FeignClientException();
    }*/

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
