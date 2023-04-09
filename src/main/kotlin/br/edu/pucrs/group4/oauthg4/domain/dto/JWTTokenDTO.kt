package br.edu.pucrs.group4.oauthg4.domain.dto

class JwtTokenDTO(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val refreshToken: String,
    val refreshTokenExpiresIn: Long
)
