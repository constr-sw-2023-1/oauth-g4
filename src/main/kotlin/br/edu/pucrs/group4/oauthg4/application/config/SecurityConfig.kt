package br.edu.pucrs.group4.oauthg4.application.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.authorizeHttpRequests()
            .requestMatchers("/actuator/**")
            .permitAll()
            .requestMatchers("/login")
            .permitAll()
            .requestMatchers("/swagger-ui/**")
            .permitAll()
            .requestMatchers("/v3/api-docs/**")
            .permitAll()
            .and()
            .authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt().and()
            .and()
            .httpBasic().and()
            .cors().and().csrf().disable()

        return httpSecurity.build()
    }
}