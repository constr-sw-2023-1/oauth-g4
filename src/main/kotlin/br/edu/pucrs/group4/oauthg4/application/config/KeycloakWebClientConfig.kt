package br.edu.pucrs.group4.oauthg4.application.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class KeycloakWebClientConfig(
    @Value("\${spring.security.oauth2.keycloak.realm.url}")
    private val baseUrl: String
) {

    @Bean
    @Qualifier("keycloakWebClient")
    fun webClient() = WebClient.builder()
        .baseUrl(baseUrl)
        .build()
}