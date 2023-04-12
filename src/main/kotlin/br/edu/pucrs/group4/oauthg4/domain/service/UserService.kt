package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak.UserRepresentation
import br.edu.pucrs.group4.oauthg4.adapter.representation.request.UpdateUserRequestDTO
import br.edu.pucrs.group4.oauthg4.domain.dto.UserDTO
import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.exception.BadRequestException
import br.edu.pucrs.group4.oauthg4.domain.exception.NotFoundException
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import java.util.UUID


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
            throw BadRequestException("Email already exists")
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

}