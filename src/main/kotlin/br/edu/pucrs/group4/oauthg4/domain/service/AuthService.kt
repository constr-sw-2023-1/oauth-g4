package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository

class AuthService(
    private val authRepository: AuthRepository
) {

    fun login(loginRequestDTO: LoginRequestDTO): JwtTokenDTO {
        return authRepository.login(loginRequestDTO)
    }

}
