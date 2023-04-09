package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository


class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: Long): User = userRepository
        .findById(id)
        .orElseThrow { Exception("User not found") }

    fun create(user: User): User {
        if (userRepository.findByEmail(user.email).isPresent) {
            throw Exception("Email already exists")
        }

        return userRepository.save(user)
    }

}