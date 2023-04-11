package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.exception.BadRequestException
import br.edu.pucrs.group4.oauthg4.domain.exception.NotFoundException
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import java.util.UUID


class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: UUID): User = userRepository
        .findById(id)
        .orElseThrow { NotFoundException("User not found") }

    fun create(user: User): User {
        if (userRepository.findByEmail(user.email).isPresent) {
            throw BadRequestException("Email already exists")
        }

        return userRepository.save(user)
    }

    fun update(user: User): User {
        if (userRepository.findById(user.id).isEmpty) {
            throw NotFoundException("User does not exists")
        }

        return userRepository.save(user)
    }

}