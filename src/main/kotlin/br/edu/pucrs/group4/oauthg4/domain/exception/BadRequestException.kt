package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    override val message: String = "Invalid input",
    override val code: String = "G4-002",
    override val status: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(message), RestException