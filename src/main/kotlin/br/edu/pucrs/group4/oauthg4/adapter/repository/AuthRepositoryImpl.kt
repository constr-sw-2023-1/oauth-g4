package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Repository
class AuthRepositoryImpl(
    private val webClient: WebClient
) : AuthRepository {

    override fun login(loginRequestDTO: LoginRequestDTO): JwtTokenDTO {
        return webClient.post()
            .uri("/protocol/openid-connect/token")
            .body(BodyInserters.fromFormData("client_id", loginRequestDTO.clientId)
                .with("client_secret", loginRequestDTO.clientSecret)
                .with("username", loginRequestDTO.username)
                .with("password", loginRequestDTO.password)
                .with("grant_type", loginRequestDTO.grantType)
            )
            .retrieve()
            .bodyToMono(JwtTokenDTO::class.java)
            .block() ?: throw RuntimeException("Error while trying to login")
    }
}