package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.exception.AuthenticationException
import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AuthServiceTest {

    private val authRepository = mockk<AuthRepository>()

    private val authService = AuthService(authRepository)

    @Test
    fun `should login successfully when credentials are valid`() {
        every { authRepository.login(any(), any()) } returns JwtTokenDTO(
            accessToken = "accessToken",
            tokenType = "tokenType",
            refreshToken = "refreshToken",
            expiresIn = 3600,
            refreshTokenExpiresIn = 3600
        )

        val result = authService.login(LoginRequest(username = "username", password = "password"))

        assertNotNull(result.accessToken)
        assertNotNull(result.tokenType)
        assertNotNull(result.refreshToken)
    }

    @Test
    fun `should throw exception when credentials are invalid`() {
        every { authRepository.login(any(), any()) } throws Exception("Invalid credentials: wrong username or password")

        assertThrows(AuthenticationException::class.java) {
            authService.login(LoginRequest(username = "username", password = "password"))
        }
    }
}