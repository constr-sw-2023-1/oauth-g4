package br.edu.pucrs.group4.oauthg4.adapter.representation.request

import com.fasterxml.jackson.annotation.JsonProperty

class CreateUserRequest(
        @JsonProperty("username")
        val username: String,
        @JsonProperty("first-name")
        val firstName: String,
        @JsonProperty("last-name")
        val lastName: String,
        @JsonProperty("password")
        val password: String,
)