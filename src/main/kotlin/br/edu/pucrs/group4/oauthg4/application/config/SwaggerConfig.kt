package br.edu.pucrs.group4.oauthg4.application.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private const val SECURITY_SCHEME_NAME = "bearerAuth"
private const val BEARER_FORMAT = "JWT"
private const val SECURITY_SCHEME_TYPE = "bearer"

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("OAuth G4")
                    .description("OAuth G4 API documentation")
                    .version("1.0.0")
            )
            .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
            .components(
                Components()
                    .addSecuritySchemes(
                        SECURITY_SCHEME_NAME, SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme(SECURITY_SCHEME_TYPE)
                            .bearerFormat(BEARER_FORMAT)
                    )
            )
    }
}
