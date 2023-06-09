package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class AuthorizationException(
    override val message: String = "User is not authorized to access this resource",
    override val code: String = "G4-003",
    override val status: HttpStatus = HttpStatus.FORBIDDEN,
) : RuntimeException(message), RestException
