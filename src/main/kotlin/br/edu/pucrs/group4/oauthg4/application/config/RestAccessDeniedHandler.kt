package br.edu.pucrs.group4.oauthg4.application.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class RestAccessDeniedHandler(
    @Qualifier("handlerExceptionResolver") private val resolver: HandlerExceptionResolver
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        resolver.resolveException(request, response, null, accessDeniedException)
    }
}
