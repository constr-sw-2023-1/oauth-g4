package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl() : UserRepository {

    override fun findAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun save(user: UserRepresentation, token: String): UserDTO {
        TODO("Not yet implemented")
    }

    override fun update(user: User): User {
        TODO("Not yet implemented")
    }

    override fun updatePassword(id: Long, password: String) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}