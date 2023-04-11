package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("username") username: String,
        @RequestParam("password") password: String,
        @RequestParam("grant_type") grantType: String
    ): ResponseEntity<JwtTokenDTO> {
        val loginResponse = authService.login(LoginRequest(username, password, grantType, clientId, clientSecret))
        return ResponseEntity.ok(loginResponse)
    }
}