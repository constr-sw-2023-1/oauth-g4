package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO

interface AuthRepository {

    fun login(username: String, password: String): JwtTokenDTO
}
