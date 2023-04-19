package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.ResetUserPasswordRequestDTO
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.exception.ExistingResourceException
import br.edu.pucrs.group4.oauthg4.domain.exception.NotFoundException
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import java.util.*


class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: UUID): User = userRepository
        .findById(id)
        .orElseThrow { NotFoundException("User not found") }

    fun create(user: UserRepresentation): UserDTO {
        if (userRepository.findByEmail(user.email).isNotEmpty()) {
            throw ExistingResourceException("User with the provided email already exists")
        }

        return userRepository.save(user)
    }

    fun update(id: UUID, request: UpdateUserRequestDTO): User {
        val user = findById(id)
        user.firstName = request.firstName ?: user.firstName
        user.lastName = request.lastName ?: user.lastName

        userRepository.update(id, request)

        return user
    }

    fun updatePassword(id: UUID, request: ResetUserPasswordRequestDTO) {
        findById(id)
        userRepository.updatePassword(id, request.password)

    }

    fun disable(id: UUID) {
        findById(id)
        userRepository.disable(id)

    }

}