package com.luckytree.shop_service.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:5173",
                        "https://127.0.0.1:5173",
                        "http://localhost:5173",
                        "http://127.0.0.1:5173",
                        "https://local.c0dewave.com:5173",
                        "https://dev-poom.c0dewave.com",
                        "https://stg-poom.c0dewave.com",
                        "https://poom.c0dewave.com",
                        "http://local-poom.c0dewave.com:5173",
                        "https://local-poom.c0dewave.com:5173")
                .allowCredentials(true)
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "OPTIONS",
                        "DELETE"
                );
    }
}
