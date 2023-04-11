package br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak

import java.util.UUID

class UserRepresentation(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
)
