package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Follow(
	val followingId: UUID = UUID.randomUUID(),
	val followerId: String,
	val followeeId: String,
	val createdAt: LocalDateTime = LocalDateTime.now()
)
