package br.edu.pucrs.group4.oauthg4.application.config

import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): OpenAPI = OpenAPI()
        .info(OpenAPI().info
            .title("OAuth G4")
            .description("OAuth G4 API documentation")
            .version("1.0.0")
        )
}
