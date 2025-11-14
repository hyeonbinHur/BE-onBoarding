package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class User(
	val userId: UUID = UUID.randomUUID(),
	val username: String,
	val email: String,
	val createdAt: LocalDateTime = LocalDateTime.now()
)