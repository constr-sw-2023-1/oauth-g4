package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class AuthorizationException(
    override val message: String,
    override val code: String = "G4-003",
    override val status: HttpStatus = HttpStatus.FORBIDDEN,
) : RuntimeException(message), RestException
