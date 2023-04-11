package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import java.util.*

interface UserRepository {

    fun findAll(): List<User>

    fun findById(id: UUID): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun save(user: UserRepresentation, token: String): UserDTO

    fun update(user: User): User

    fun updatePassword(id: Long, password: String)

    fun delete(id: Long)

}
