package br.edu.pucrs.group4.oauthg4.domain.exception

import org.springframework.http.HttpStatus

interface RestException {
    val message: String
    val code: String
    val status: HttpStatus
}