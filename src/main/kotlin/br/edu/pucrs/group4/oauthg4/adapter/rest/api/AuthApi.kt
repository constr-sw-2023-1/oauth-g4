package br.edu.pucrs.group4.oauthg4.adapter.rest.api

import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserInformationDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity

interface AuthApi {

    @Operation(summary = "Authenticate user and generate a JWT token")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "JWT token generated"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun login(clientId: String, clientSecret: String, username: String, password: String, grantType: String): ResponseEntity<JwtTokenDTO>

    @Operation(summary = "Refresh JWT token")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "JWT token refreshed"),
            ApiResponse(responseCode = "401", description = "Invalid token"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun refresh(clientId: String, clientSecret: String, refreshToken: String, grantType: String): ResponseEntity<JwtTokenDTO>

    @Operation(summary = "Retrieve user own information")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User data retrieved"),
            ApiResponse(responseCode = "401", description = "Invalid token"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun getMe(): ResponseEntity<UserInformationDTO>
}
