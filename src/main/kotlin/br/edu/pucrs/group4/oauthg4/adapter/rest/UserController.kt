package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.CreateUserRequest
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import br.edu.pucrs.group4.oauthg4.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/users")
    fun create(
        @RequestBody() user: CreateUserRequest,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<UserDTO> {
        val createResponse = userService.create(UserRepresentation(
                id = UUID.randomUUID(),
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                email = user.username,
                credentials = listOf(mapOf("type" to "password", "value" to user.password, "temporary" to false))),
                token)
        return ResponseEntity.ok(createResponse)
    }
}