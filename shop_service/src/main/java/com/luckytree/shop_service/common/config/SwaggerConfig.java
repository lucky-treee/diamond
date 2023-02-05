package com.luckytree.shop_service.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


// SwaggerConfig.java
@Configuration
public class SwaggerConfig{

    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("test")
                .version(springdocVersion)
                .description("API에 대한 설명 부분");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
