package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Repository
class AuthRepositoryImpl(
    private val webClient: WebClient
) : AuthRepository {

    override fun login(loginRequest: LoginRequest): JwtTokenDTO {
        return webClient.post()
            .uri("/realms/construcao-sw/protocol/openid-connect/token")
            .body(BodyInserters.fromFormData("client_id", loginRequest.clientId)
                .with("client_secret", loginRequest.clientSecret)
                .with("username", loginRequest.username)
                .with("password", loginRequest.password)
                .with("grant_type", loginRequest.grantType)
            )
            .retrieve()
            .bodyToMono(JwtTokenDTO::class.java)
            .block() ?: throw RuntimeException("Error while trying to login")
    }
}