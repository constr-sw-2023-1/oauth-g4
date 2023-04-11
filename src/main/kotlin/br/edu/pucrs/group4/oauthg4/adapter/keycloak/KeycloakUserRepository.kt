package br.edu.pucrs.group4.oauthg4.adapter.keycloak

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import java.util.*

@FeignClient(name = "keycloak", url = "\${spring.security.oauth2.keycloak.realm.url}")
interface KeycloakUserRepository {

    @GetMapping("/users")
    fun findAll(): List<User>

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: UUID): Optional<User>

    @PostMapping("/users")
    fun save(user: UserRepresentation)

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Long, user: User): User

    @PatchMapping("/users/{id}")
    fun updatePassword(@PathVariable id: Long, password: String)

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long)
}