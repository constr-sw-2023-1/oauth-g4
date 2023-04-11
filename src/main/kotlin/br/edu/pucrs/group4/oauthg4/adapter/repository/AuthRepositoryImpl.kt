package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.keycloak.KeycloakAuthRepository
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository
import org.springframework.stereotype.Repository

@Repository
class AuthRepositoryImpl(
    private val keycloakAuthRepository: KeycloakAuthRepository
) : AuthRepository {

    override fun login(loginRequest: LoginRequest): JwtTokenDTO {
        return keycloakAuthRepository.login(
            clientId = loginRequest.clientId,
            clientSecret = loginRequest.clientSecret,
            username = loginRequest.username,
            password = loginRequest.password,
            grantType = loginRequest.grantType
        )
    }
}