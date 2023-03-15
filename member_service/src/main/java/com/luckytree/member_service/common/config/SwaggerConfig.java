package com.luckytree.member_service.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Member API")
                .version("v1")
                .description("회원");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    public OpenApiCustomizer customerGolbalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();
                ApiResponse ok = new ApiResponse().description("ok")
                        .content(new Content()
                                .addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType()));
                apiResponses.addApiResponse("200", ok);
                ApiResponse badRequest = new ApiResponse().description("Bad Request")
                        .content(new Content()
                                .addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType()));
                apiResponses.addApiResponse("400", badRequest);
                ApiResponse serverError = new ApiResponse().description("Server Error")
                        .content(new Content()
                                .addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType()));
                apiResponses.addApiResponse("500", serverError);
            }));
        };
    }
}
