package br.edu.pucrs.group4.oauthg4.adapter.representation.request


data class LoginRequest(
    val username: String,
    val password: String,
    val grantType: String,
    val clientId: String,
    val clientSecret: String
)