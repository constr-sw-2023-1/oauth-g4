package br.edu.pucrs.group4.oauthg4.adapter.representation.request


data class RefreshTokenRequestDTO(
    val refreshToken: String,
    val grantType: String,
    val clientId: String,
    val clientSecret: String
)