package br.edu.pucrs.group4.oauthg4.adapter.representation.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email

class CreateUserRequestDTO(
        @Email(message = "Invalid email")
        @JsonProperty("username")
        val username: String,
        @JsonProperty("first-name")
        val firstName: String,
        @JsonProperty("last-name")
        val lastName: String,
        @JsonProperty("password")
        val password: String,
)