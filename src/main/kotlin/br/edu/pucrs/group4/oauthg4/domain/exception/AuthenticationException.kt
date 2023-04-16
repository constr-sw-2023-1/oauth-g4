package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class AuthenticationException(
    override val message: String = "Invalid or missing credentials",
    override val code: String = "G4-001",
    override val status: HttpStatus = HttpStatus.UNAUTHORIZED)
    : RuntimeException(message), RestException