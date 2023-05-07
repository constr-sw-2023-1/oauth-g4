package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.RefreshTokenRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.exception.AuthenticationException
import br.edu.pucrs.group4.oauthg4.domain.exception.BadRequestException
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository

class AuthService(
    private val authRepository: AuthRepository
) {

    fun login(loginRequest: LoginRequestDTO): JwtTokenDTO {
        if (loginRequest.clientId.isBlank() || loginRequest.clientSecret.isBlank() || loginRequest.username.isBlank() || loginRequest.password.isBlank() || loginRequest.grantType.isBlank()) {
            throw BadRequestException("Missing parameters")
        }

        return try {
            authRepository.login(loginRequest)
        } catch (e: Exception) {
            throw AuthenticationException("Error while trying to login: " + e.message)
        }
    }

    fun refresh(refreshTokenRequest: RefreshTokenRequestDTO): JwtTokenDTO {
        if (refreshTokenRequest.clientId.isBlank() || refreshTokenRequest.clientSecret.isBlank() || refreshTokenRequest.refreshToken.isBlank() || refreshTokenRequest.grantType.isBlank()) {
            throw BadRequestException("Missing parameters")
        }

        return try {
            authRepository.refresh(refreshTokenRequest)
        } catch (e: Exception) {
            throw AuthenticationException("Error while trying to login: " + e.message)
        }
    }

}
