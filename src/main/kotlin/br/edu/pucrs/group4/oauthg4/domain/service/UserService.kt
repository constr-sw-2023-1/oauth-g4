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

    fun findAll(token: String): List<User> {
        return userRepository.findAll(token)
    }

    fun findById(id: UUID, token: String): User = userRepository
        .findById(id, token = token)
        .orElseThrow { NotFoundException("User not found") }

    fun create(user: UserRepresentation, token: String): UserDTO {
        if (userRepository.findByEmail(user.email).isPresent) {
            throw ExistingResourceException("Email already exists")
        }

        return userRepository.save(user, token)
    }

    fun update(id: UUID, request: UpdateUserRequestDTO, token: String): User {
        val user = findById(id, token)
        user.firstName = request.firstName
        user.lastName = request.lastName

        userRepository.update(id, request, token)

        return user;
    }

    fun updatePassword(id: UUID, request: ResetUserPasswordRequestDTO, token: String) {
        findById(id, token)
        userRepository.updatePassword(id, request.password, token)

    }

    fun disable(id: UUID, token: String) {
        findById(id, token)
        userRepository.disable(id, token)

    }

}