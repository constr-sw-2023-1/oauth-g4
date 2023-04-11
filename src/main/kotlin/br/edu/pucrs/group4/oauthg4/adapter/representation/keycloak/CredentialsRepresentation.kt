package br.edu.pucrs.group4.oauthg4.adapter.representation.keycloak

import java.util.*

class CredentialsRepresentation (
    val type: String,
    val value: String,
    val temporary: Boolean
)