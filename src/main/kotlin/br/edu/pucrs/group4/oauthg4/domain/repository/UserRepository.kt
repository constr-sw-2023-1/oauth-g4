package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import java.util.*

interface UserRepository {

    fun findAll(token: String): List<User>

    fun findById(id: UUID, token: String): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun save(user: UserRepresentation, token: String): UserDTO

    fun update(id: UUID, user: UpdateUserRequestDTO, token: String)

    fun updatePassword(id: UUID, password: String, token: String)

    fun disable(id: UUID, token: String)

}
