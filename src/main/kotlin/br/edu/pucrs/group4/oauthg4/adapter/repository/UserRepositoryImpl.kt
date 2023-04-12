package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.keycloak.KeycloakUserRepository
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl(
    private val keycloakUserRepository: KeycloakUserRepository
) : UserRepository {
    override fun findAll(token: String): List<User> {
        return keycloakUserRepository.findAll(token)
    }

    override fun findById(id: UUID, token: String): Optional<User> {
        return keycloakUserRepository.findById(id, token)
    }

    override fun findByEmail(email: String): Optional<User> {
        return Optional.empty()
    }

    override fun save(user: UserRepresentation, token: String): UserDTO {
        val saveResponse = keycloakUserRepository.save(user, token)

        return UserDTO(user.id.toString(),user.username,user.firstName,user.lastName,true)
    }

    override fun update(id:UUID, user: UpdateUserRequestDTO, token: String) {
        keycloakUserRepository.update(id, user, token)
    }

    override fun updatePassword(id: UUID, password: String, token: String) {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID) {
        keycloakUserRepository.delete(id)
    }
}