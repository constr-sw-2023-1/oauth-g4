package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<JwtTokenDTO> {
        val loginResponse = authService.login(loginRequest)
        return ResponseEntity.ok(loginResponse)
    }
}