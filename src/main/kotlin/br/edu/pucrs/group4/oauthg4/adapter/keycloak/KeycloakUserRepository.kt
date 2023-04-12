package br.edu.pucrs.group4.oauthg4.adapter.keycloak

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.CredentialsRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.DisableUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import java.util.*

@FeignClient(name = "keycloak", url = "\${spring.security.oauth2.keycloak.realm.admin-url}")
interface KeycloakUserRepository {

    @GetMapping("/users")
    fun findAll(@RequestHeader("Authorization") token: String): List<User>

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: UUID, @RequestHeader("Authorization") token: String): Optional<User>

    @PostMapping("/users")
    fun save(@RequestBody user: UserRepresentation, @RequestHeader("Authorization") token: String)

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: UUID, @RequestBody user: UpdateUserRequestDTO, @RequestHeader("Authorization") token: String)

    @PutMapping("/users/{id}/reset-password")
    fun updatePassword(@PathVariable id: UUID, @RequestBody credentials: CredentialsRepresentation, @RequestHeader("Authorization") token: String)

    @PutMapping("/users/{id}")
    fun disable(@PathVariable id: UUID, @RequestBody user: DisableUserRequestDTO, @RequestHeader("Authorization") token: String)
}