package br.edu.pucrs.group4.oauthg4.application.config

import br.edu.pucrs.group4.oauthg4.domain.repository.AuthRepository
import br.edu.pucrs.group4.oauthg4.domain.repository.UserRepository
import br.edu.pucrs.group4.oauthg4.domain.service.AuthService
import br.edu.pucrs.group4.oauthg4.domain.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean
    fun userService(userRepository: UserRepository) = UserService(userRepository)

    @Bean
    fun authService(authRepository: AuthRepository) = AuthService(authRepository)
}