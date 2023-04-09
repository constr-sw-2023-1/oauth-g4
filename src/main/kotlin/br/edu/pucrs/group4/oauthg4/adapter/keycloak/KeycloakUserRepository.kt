package br.edu.pucrs.group4.oauthg4.adapter.keycloak

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import java.util.*

@FeignClient(name = "keycloak", url = "\${keycloak.realm.url}")
interface KeycloakUserRepository {

    @GetMapping("/users")
    fun findAll(): List<User>

    @GetMapping("/users/{id}")
    fun findById(id: Long): Optional<User>

    @PostMapping("/users")
    fun save(user: User): User

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Long, user: User): User

    @PatchMapping("/users/{id}")
    fun updatePassword(@PathVariable id: Long, password: String)

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long)
}