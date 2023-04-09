package br.edu.pucrs.group4.oauthg4.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    val id: Long,
    val name: String,
    val email: String,
)
