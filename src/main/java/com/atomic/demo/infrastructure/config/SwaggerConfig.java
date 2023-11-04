package com.atomic.demo.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "사전 과제 API 명세서",
        description = "결제, 취소, 트랜잭션 검색",
        version = "v1"
    )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder()
            .group("Payment API")
            .pathsToMatch("/api/payment/**")
            .build();
    }
}