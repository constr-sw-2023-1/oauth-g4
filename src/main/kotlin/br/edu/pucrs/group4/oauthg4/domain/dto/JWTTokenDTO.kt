package br.edu.pucrs.group4.oauthg4.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

class JwtTokenDTO(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Long,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    @JsonProperty("refresh_token_expires_in")
    val refreshTokenExpiresIn: Long
)
