package br.edu.pucrs.group4.oauthg4.adapter.representation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CreateUserRequestDTO(
    @field:NotBlank(message = "Username is required")
    @field:Email(message = "Invalid email", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    val username: String,
    @field:NotBlank(message = "First name is required")
    @JsonProperty("first-name")
    val firstName: String,
    @field:NotBlank(message = "Last name is required")
    @JsonProperty("last-name")
    val lastName: String,
    val password: String,
)