package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.keycloak.KeycloakUserClient
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.CredentialsRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.DisableUserRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.exception.AuthenticationException
import br.edu.pucrs.group4.oauthg4.domain.exception.AuthorizationException
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import feign.FeignException
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl(
    private val keycloakUserClient: KeycloakUserClient
) : UserRepository {
    override fun findAll(token: String): List<User> {
        return try {
            keycloakUserClient.findAll(token)
        } catch (exception: FeignException) {
            if (exception.status() == 401) {
                throw AuthenticationException("Invalid token")
            }
            if (exception.status() == 403) {
                throw AuthorizationException("Forbidden")
            }
            throw exception
        }
    }

    override fun findById(id: UUID, token: String): Optional<User> {
        return try {
            keycloakUserClient.findById(id, token)
        } catch (exception: FeignException) {
            if (exception.status() == 401) {
                throw AuthenticationException("Invalid token")
            }
            if (exception.status() == 403) {
                throw AuthorizationException("Forbidden")
            }
            throw exception
        }
    }

    override fun findByEmail(email: String): Optional<User> {
        return Optional.empty()
    }

    override fun save(user: UserRepresentation, token: String): UserDTO {

        try {
            val response = keycloakUserClient.save(user, token)
            val id = response.headers.location.toString().split("/").last()
            return UserDTO(id, user.username, user.firstName, user.lastName, true)

        } catch (exception: FeignException) {
            if (exception.status() == 401) {
                throw AuthenticationException("Invalid token")
            }
            if (exception.status() == 403) {
                throw AuthorizationException("Forbidden")
            }
            throw exception
        }
    }

    override fun update(id: UUID, user: UpdateUserRequestDTO, token: String) {
        try {
            keycloakUserClient.update(id, user, token)
        } catch (exception: FeignException) {
            if (exception.status() == 401) {
                throw AuthenticationException("Invalid token")
            }
            if (exception.status() == 403) {
                throw AuthorizationException("Forbidden")
            }
            throw exception
        }
    }

    override fun updatePassword(id: UUID, password: String, token: String) {
        keycloakUserClient.updatePassword(id, CredentialsRepresentation("password",password,false), token)
    }

    override fun disable(id: UUID, token: String) {
        try {
            keycloakUserClient.disable(id, DisableUserRequestDTO() ,token)

        } catch (exception: FeignException) {
            if (exception.status() == 401) {
                throw AuthenticationException("Invalid token")
            }
            if (exception.status() == 403) {
                throw AuthorizationException("Forbidden")
            }
            throw exception
        }
    }
}