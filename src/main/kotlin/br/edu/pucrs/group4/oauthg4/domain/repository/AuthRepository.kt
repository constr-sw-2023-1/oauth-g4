package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.RefreshTokenRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO

interface AuthRepository {

    fun login(loginRequestDTO: LoginRequestDTO): JwtTokenDTO

    fun refresh(refreshTokenRequestDTO: RefreshTokenRequestDTO): JwtTokenDTO
}
