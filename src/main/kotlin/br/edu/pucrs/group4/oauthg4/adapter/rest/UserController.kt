package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.CreateUserRequest
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.LoginRequest
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.JwtTokenDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import br.edu.pucrs.group4.oauthg4.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun get(
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<List<User>> {
        val users = userService.findAll(token)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: UUID,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<User> {
        val user = userService.findById(id, token)
        return ResponseEntity.ok(user)
    }

    @PostMapping
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

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody user: UpdateUserRequestDTO,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<User> {
        val updateResponse = userService.update(
            id,
            user,
                token)
        return ResponseEntity.ok(updateResponse)
    }
}