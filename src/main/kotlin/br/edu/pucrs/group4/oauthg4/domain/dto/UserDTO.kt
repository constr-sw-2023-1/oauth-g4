package br.edu.pucrs.group4.oauthg4.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserDTO(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("first-name")
    val firstName: String,
    @JsonProperty("last-name")
    val lastName: String,
    @JsonProperty("enabled")
    val enabled: Boolean
)
