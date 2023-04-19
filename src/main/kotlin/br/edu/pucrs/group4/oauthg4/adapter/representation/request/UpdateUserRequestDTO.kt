package br.edu.pucrs.group4.oauthg4.adapter.representation.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUserRequestDTO(
    var firstName: String?,
    var lastName: String?
)