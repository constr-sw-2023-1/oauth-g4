package br.edu.pucrs.group4.oauthg4.adapter.rest

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.CreateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.ResetUserPasswordRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.rest.api.UserApi
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('admin')")
class UserController(
    private val userService: UserService
) : UserApi {

    @GetMapping
    override fun get(
        @RequestHeader("Authorization", required = true) token: String
    ): ResponseEntity<List<User>> {
        val users = userService.findAll(token)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    override fun get(
        @PathVariable id: UUID,
        @RequestHeader("Authorization", required = true) token: String
    ): ResponseEntity<User> {
        val user = userService.findById(id, token)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    override fun create(
        @Valid @RequestBody user: CreateUserRequestDTO,
        @RequestHeader("Authorization", required = true) token: String
    ): ResponseEntity<UserDTO> {
        val createResponse = userService.create(
            UserRepresentation(
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                email = user.username,
                credentials = listOf(mapOf("type" to "password", "value" to user.password, "temporary" to false))
            ),
            token
        )
        return ResponseEntity.ok(createResponse)
    }

    @PutMapping("/{id}")
    override fun update(
        @PathVariable id: UUID,
        @RequestBody user: UpdateUserRequestDTO,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<User> {
        val updateResponse = userService.update(
            id,
            user,
            token
        )
        return ResponseEntity.ok(updateResponse)
    }

    @PatchMapping("/{id}")
    override fun resetPassword(
        @PathVariable id: UUID,
        @RequestBody password: ResetUserPasswordRequestDTO,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<Unit> {
        userService.updatePassword(
            id,
            password,
            token
        )
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    override fun disable(
        @PathVariable id: UUID,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<Unit> {
        userService.disable(id, token)
        return ResponseEntity.ok().build()
    }

}