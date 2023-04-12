package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    override val message: String = "Resource not found",
    override val code: String = "G4-004",
    override val status: HttpStatus = HttpStatus.NOT_FOUND,
) : RuntimeException(message), RestException
