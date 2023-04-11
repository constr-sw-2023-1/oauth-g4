package br.edu.pucrs.group4.oauthg4.adapter.repository

import  br.edu.pucrs.group4.oauthg4.adapter.keycloak.KeycloakUserRepository
import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl(
    private val keycloakUserRepository: KeycloakUserRepository
) : UserRepository {
    override fun findAll(): List<User> {
        return keycloakUserRepository.findAll()
    }

    override fun findById(id: UUID): Optional<User> {
        return keycloakUserRepository.findById(id)
    }

    override fun findByEmail(email: String): Optional<User> {
        return Optional.empty()
    }

    override fun save(user: User): User {
        keycloakUserRepository.save(
            UserRepresentation(
                user.id,
                user.firstName,
                user.lastName,
                user.username,
                user.email
            )
        )

        return user
    }

    override fun update(user: User): User {
        return null!!
    }

    override fun updatePassword(id: Long, password: String) {
    }

    override fun delete(id: Long) {
        keycloakUserRepository.delete(id)
    }
}