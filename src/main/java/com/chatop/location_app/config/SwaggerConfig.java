package com.chatop.location_app.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
            .group("app")
            .packagesToScan("com.chatop.location_app.controller")
            .pathsToMatch("/**")
            .build();
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("location app - Chatop API")
                    .description("location app - Chatop API")
                    .version("v1.0.0")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                    .description("location app - Chatop API Wiki Documentation")
                    .url("https://springshop.wiki.github.org/docs"));
  }

}