package br.edu.pucrs.group4.oauthg4.adapter.repository

import br.edu.pucrs.group4.oauthg4.adapter.database.jpa.JPAUserRepository
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepositoryImpl(
    private val jpaRepository: JPAUserRepository
) : UserRepository {
    override fun findAll(): List<User> {
        return jpaRepository.findAll()
    }

    override fun findById(id: Long): Optional<User> {
        return jpaRepository.findById(id)
    }

    override fun findByEmail(email: String): Optional<User> {
        return jpaRepository.findByEmail(email)
    }

    override fun save(user: User): User {
        return jpaRepository.save(user)
    }

    override fun update(user: User): User {
        return jpaRepository.save(user)
    }

    override fun updatePassword(id: Long, password: String) {
        jpaRepository.updatePasswordById(id, password)
    }

    override fun delete(id: Long) {
        jpaRepository.deleteById(id)
    }
}