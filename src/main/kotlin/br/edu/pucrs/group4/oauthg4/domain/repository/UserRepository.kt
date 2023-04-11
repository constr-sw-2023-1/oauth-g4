package br.edu.pucrs.group4.oauthg4.domain.repository

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import java.util.*

interface UserRepository {

    fun findAll(): List<User>

    fun findById(id: UUID): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun save(user: User): User

    fun update(user: User): User

    fun updatePassword(id: Long, password: String)

    fun delete(id: Long)

}
