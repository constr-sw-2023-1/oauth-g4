package br.edu.pucrs.group4.oauthg4.adapter.representation.request

class LoginRequest(
    val username: String,
    val password: String,
    val grantType: String,
    val clientId: String,
    val clientSecret: String
)