package br.edu.pucrs.group4.oauthg4.domain.service

import br.edu.pucrs.group4.oauthg4.domain.entity.User
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.*

class UserServiceTest {

    private val userRepository = mockk<UserRepository>()

    private val userService = UserService(userRepository)

    @Test
    fun `should find all users`() {
        every { userRepository.findAll() } returns listOf()

        val users = userService.findAll()

        assertEquals(0, users.size)
    }

    @Test
    fun `should find user by id when user exists`() {
        val user = User(1, "name", "email")
        every { userRepository.findById(1) } returns Optional.of(user)

        val userFound = userService.findById(1)

        assertEquals(user, userFound)
    }

    @Test
    fun `should throw exception when user not found`() {
        every { userRepository.findById(1) } returns Optional.empty()

        assertThrows<Exception>("User not found") { userService.findById(1) }
    }

    @Test
    fun `should create user`() {
        val user = User(1, "name", "email")
        every { userRepository.findByEmail("email") } returns Optional.empty()
        every { userRepository.save(user) } returns user

        val userCreated = userService.create(user)

        assertEquals(user, userCreated)
    }

    @Test
    fun `should throw exception when email already exists`() {
        val user = User(1, "name", "email")
        every { userRepository.findByEmail("email") } returns Optional.of(user)

        assertThrows<Exception>("Email already exists") { userService.create(user) }
    }

}