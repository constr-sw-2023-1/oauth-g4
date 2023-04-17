package br.edu.pucrs.group4.oauthg4.adapter.rest.api

import br.edu.pucrs.group4.oauthg4.adapter.representation.request.CreateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.ResetUserPasswordRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import java.util.UUID

interface UserApi {

    @Operation(summary = "Get all users")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Users found"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun get(): ResponseEntity<List<User>>

    @Operation(summary = "Get user by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User found"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun get(id: UUID): ResponseEntity<User>

    @Operation(summary = "Create a new user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "User created"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "409", description = "User already exists"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun create(user: CreateUserRequestDTO): ResponseEntity<UserDTO>

    @Operation(summary = "Update user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User updated"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun update(id: UUID, user: UpdateUserRequestDTO): ResponseEntity<User>

    @Operation(summary = "Update user password")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User password updated"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun resetPassword(id: UUID, password: ResetUserPasswordRequestDTO): ResponseEntity<Unit>

    @Operation(summary = "Disable user")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "User disabled"),
            ApiResponse(responseCode = "400", description = "Invalid request"),
            ApiResponse(responseCode = "401", description = "Invalid credentials"),
            ApiResponse(responseCode = "403", description = "Access denied"),
            ApiResponse(responseCode = "404", description = "Resource not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    fun disable(id: UUID): ResponseEntity<Unit>


}