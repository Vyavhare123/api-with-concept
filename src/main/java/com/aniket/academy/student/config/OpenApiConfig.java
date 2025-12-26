package com.aniket.academy.student.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Student Management API",
                version = "1.0.0",
                description = "Industry standard REST APIs By Aniket"
        )
)
public class OpenApiConfig {
        // This ic configuration class for swagger implementation
}
