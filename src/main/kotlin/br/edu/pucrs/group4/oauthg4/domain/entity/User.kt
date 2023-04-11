package br.edu.pucrs.group4.oauthg4.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
)
