package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.RefreshTokenRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.rest.api.AuthApi
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserInformationDTO
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
): AuthApi {
    @PostMapping("/login")
    override fun login(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("username") username: String,
        @RequestParam("password") password: String,
        @RequestParam("grant_type") grantType: String
    ): ResponseEntity<JwtTokenDTO> {
        val loginResponse = authService.login(LoginRequestDTO(username, password, grantType, clientId, clientSecret))
        return ResponseEntity.ok(loginResponse)
    }

    @PostMapping("/token/refresh")
    override fun refresh(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("refresh_token") refreshToken: String,
        @RequestParam("grant_type") grantType: String
    ): ResponseEntity<JwtTokenDTO> {
        val refreshTokenResponse = authService.refresh(RefreshTokenRequestDTO(refreshToken,grantType,clientId,clientSecret))
        return ResponseEntity.ok(refreshTokenResponse)
    }

    @GetMapping("/token/validate")
    override fun validateToken(): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }


    @GetMapping("/me")
    override fun getMe(
    ): ResponseEntity<UserInformationDTO> {
        val userInformationResponse = authService.getMe()
        return ResponseEntity.ok(userInformationResponse)
    }
}