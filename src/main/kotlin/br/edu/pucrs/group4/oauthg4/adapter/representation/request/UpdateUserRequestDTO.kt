package br.edu.pucrs.group4.oauthg4.adapter.representation.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserRequestDTO(
    @JsonProperty("first-name")
    val firstName: String,
    @JsonProperty("last-name")
    val lastName: String
)