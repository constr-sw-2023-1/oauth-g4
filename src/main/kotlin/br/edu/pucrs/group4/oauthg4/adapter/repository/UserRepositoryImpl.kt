package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.keycloak.KeycloakUserClient
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.CredentialsRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.DisableUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl(
    private val keycloakUserClient: KeycloakUserClient
) : UserRepository {
    override fun findAll(): List<User> {
        return keycloakUserClient.findAll()
    }

    override fun findById(id: UUID): Optional<User> {
        return keycloakUserClient.findById(id)
    }

    override fun findByEmail(email: String): List<User> {
        return keycloakUserClient.findByEmail(email)
    }

    override fun save(user: UserRepresentation): UserDTO {
        val response = keycloakUserClient.save(user)
        val id = response.headers.location.toString().split("/").last()
        return UserDTO(id, user.username, user.firstName, user.lastName, true)

    }

    override fun update(id: UUID, user: UpdateUserRequestDTO) {
        keycloakUserClient.update(id, user)

    }

    override fun updatePassword(id: UUID, password: String) {
        keycloakUserClient.updatePassword(id, CredentialsRepresentation("password", password, false))
    }

    override fun disable(id: UUID) {
        keycloakUserClient.disable(id, DisableUserRequestDTO())
    }
}