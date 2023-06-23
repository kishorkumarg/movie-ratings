package com.movie.application.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    GroupedOpenApi propertiesApis() {
        return GroupedOpenApi.builder()
                .group("properties")
                .pathsToMatch("/**").build();
    }

//    @Bean
//    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//        return new SwaggerUiConfigParameters();
//    }
}
