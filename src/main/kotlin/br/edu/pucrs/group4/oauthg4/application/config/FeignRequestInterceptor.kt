package br.edu.pucrs.group4.oauthg4.application.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component

@Component
class FeignRequestInterceptor : RequestInterceptor {


    override fun apply(template: RequestTemplate) {
        val authentication = (SecurityContextHolder.getContext().authentication) as JwtAuthenticationToken
        val token = (authentication.principal as Jwt).tokenValue

        template.header(AUTHORIZATION, "Bearer $token")
    }


}
