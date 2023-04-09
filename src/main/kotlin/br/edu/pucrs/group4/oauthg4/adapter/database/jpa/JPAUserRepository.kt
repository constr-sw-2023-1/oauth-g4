package br.edu.pucrs.group4.oauthg4.adapter.database.jpa

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JPAUserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun updatePasswordById(id: Long, password: String)
}