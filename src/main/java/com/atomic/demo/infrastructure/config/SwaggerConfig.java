package com.atomic.demo.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "분산 처리 환경 트랜 잭션 프로젝트 API",
        description = "결제 요청",
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