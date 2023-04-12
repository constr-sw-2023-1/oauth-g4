package br.edu.pucrs.group4.oauthg4.adapter.rest.error

import br.edu.pucrs.group4.oauthg4.adapter.representation.error.ErrorResponse
import br.edu.pucrs.group4.oauthg4.domain.exception.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(
        value = [
            BadRequestException::class,
            NotFoundException::class,
            AuthenticationException::class,
            AuthorizationException::class,
            ExistingResourceException::class,
        ]
    )
    fun handleRestException(ex: RestException): ResponseEntity<ErrorResponse> {
        return buildResponseEntity(ex)
    }

    private fun buildResponseEntity(exception: RestException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            exception.code,
            exception.message
        )
        return ResponseEntity.status(exception.status).body(response)
    }
}
