package br.edu.pucrs.group4.oauthg4.adapter.rest.error

import br.edu.pucrs.group4.oauthg4.adapter.representation.error.ErrorResponse
import br.edu.pucrs.group4.oauthg4.domain.exception.*
import feign.FeignException
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
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

    @ExceptionHandler(
        value = [ FeignException::class ]
    )
    fun handleFeignException(exception: FeignException): ResponseEntity<ErrorResponse> {
        return handleApiException(exception)
    }

    private fun handleApiException(exception: FeignException): ResponseEntity<ErrorResponse> {
        val restException : RestException = when (exception.status()) {
            401 -> AuthenticationException()
            403 -> AuthorizationException()
            404 -> NotFoundException()
            409 -> ExistingResourceException()
            else -> BadRequestException(exception.message!!)
        }

        return buildResponseEntity(restException)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse>
    {
        val errors = exception.bindingResult.allErrors

        val badRequestException = BadRequestException("Validation error: ${errors.first().defaultMessage!!}")

        return buildResponseEntity(badRequestException)
    }

    private fun buildResponseEntity(exception: RestException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            exception.code,
            exception.message
        )
        return ResponseEntity.status(exception.status).body(response)
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException::class)
    fun handleAuthenticationException(authException: org.springframework.security.core.AuthenticationException): ResponseEntity<ErrorResponse> {
        return buildResponseEntity(AuthenticationException())
    }

    @ExceptionHandler(InvalidBearerTokenException::class)
    fun handleInvalidBearerTokenException(authException: InvalidBearerTokenException): ResponseEntity<ErrorResponse> {
        return buildResponseEntity(AuthenticationException())
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(authException: AccessDeniedException): ResponseEntity<ErrorResponse> {
        return buildResponseEntity(AuthorizationException())
    }
}
