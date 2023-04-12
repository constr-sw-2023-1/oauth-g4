package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class ExistingResourceException(
    override val message: String,
    override val code: String = "G4-009",
    override val status: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(message), RestException
