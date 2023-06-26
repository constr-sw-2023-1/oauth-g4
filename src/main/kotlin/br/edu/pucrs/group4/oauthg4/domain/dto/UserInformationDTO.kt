package br.edu.pucrs.group4.oauthg4.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserInformationDTO(
    val username: String,
    @JsonProperty("full_name")
    val fullName: String,
    val roles: Set<String>
)