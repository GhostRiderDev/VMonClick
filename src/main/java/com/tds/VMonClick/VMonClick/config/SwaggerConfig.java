package com.tds.VMonClick.VMonClick.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
class SwaggerConfig {
  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("VMonClick API").version("1.0.0"))
        .security(List.of(new SecurityRequirement().addList("bearerAuth")));
  }
}
