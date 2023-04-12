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
    override fun findAll(token: String): List<User> {
        return keycloakUserClient.findAll(token)
    }

    override fun findById(id: UUID, token: String): Optional<User> {
        return keycloakUserClient.findById(id, token)
    }

    override fun findByEmail(email: String, token: String): List<User> {
        return keycloakUserClient.findByEmail(email, token)
    }

    override fun save(user: UserRepresentation, token: String): UserDTO {
        val response = keycloakUserClient.save(user, token)
        val id = response.headers.location.toString().split("/").last()
        return UserDTO(id, user.username, user.firstName, user.lastName, true)

    }

    override fun update(id: UUID, user: UpdateUserRequestDTO, token: String) {
        keycloakUserClient.update(id, user, token)

    }

    override fun updatePassword(id: UUID, password: String, token: String) {
        keycloakUserClient.updatePassword(id, CredentialsRepresentation("password", password, false), token)
    }

    override fun disable(id: UUID, token: String) {
        keycloakUserClient.disable(id, DisableUserRequestDTO(), token)
    }
}