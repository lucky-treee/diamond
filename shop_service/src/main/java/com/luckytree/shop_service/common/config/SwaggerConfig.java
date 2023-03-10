package com.luckytree.shop_service.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// SwaggerConfig.java
@Configuration
public class SwaggerConfig{

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Shop Service API")
                .version("v1")
                .description("Shop Service api 명세서");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
