package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import java.util.*

interface UserRepository {

    fun findAll(): List<User>

    fun findById(id: UUID): Optional<User>

    fun findByEmail(email: String): List<User>

    fun save(user: UserRepresentation): UserDTO

    fun update(id: UUID, user: UpdateUserRequestDTO)

    fun updatePassword(id: UUID, password: String)

    fun disable(id: UUID)

}
