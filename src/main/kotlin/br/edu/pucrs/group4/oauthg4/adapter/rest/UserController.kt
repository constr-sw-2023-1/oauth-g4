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
@PreAuthorize("hasAuthority('ADMIN')")
class UserController(
    private val userService: UserService
) : UserApi {

    @GetMapping
    override fun get(): ResponseEntity<List<User>> {
        val users = userService.findAll()

        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    override fun get(
        @PathVariable id: UUID
    ): ResponseEntity<User> {
        val user = userService.findById(id)
        return ResponseEntity.ok(user)
    }

    @PostMapping
    override fun create(
        @Valid @RequestBody user: CreateUserRequestDTO
    ): ResponseEntity<UserDTO> {
        val createResponse = userService.create(
            UserRepresentation(
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                email = user.username,
                credentials = listOf(mapOf("type" to "password", "value" to user.password, "temporary" to false))
            )
        )
        return ResponseEntity.ok(createResponse)
    }

    @PutMapping("/{id}")
    override fun update(
        @PathVariable id: UUID,
        @RequestBody user: UpdateUserRequestDTO
    ): ResponseEntity<User> {
        val updateResponse = userService.update(
            id,
            user
        )
        return ResponseEntity.ok(updateResponse)
    }

    @PatchMapping("/{id}")
    override fun resetPassword(
        @PathVariable id: UUID,
        @RequestBody password: ResetUserPasswordRequestDTO
    ): ResponseEntity<Unit> {
        userService.updatePassword(
            id,
            password
        )
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    override fun disable(
        @PathVariable id: UUID
    ): ResponseEntity<Unit> {
        userService.disable(id)
        return ResponseEntity.ok().build()
    }

}