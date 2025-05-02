package com.example.beat_api_sileo.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Beat API Sileo")
                        .version("1.0.0")
                        .description("API para gerenciamento de beats de usuários."));
    }
}
