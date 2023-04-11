package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.exception.AuthenticationException
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository

class AuthService(
    private val authRepository: AuthRepository
) {

    fun login(loginRequest: LoginRequest): JwtTokenDTO {
        return try {
            authRepository.login(loginRequest)
        } catch (exception: Exception) {
            throw AuthenticationException("Invalid credentials: wrong username or password")
        }
    }

}
