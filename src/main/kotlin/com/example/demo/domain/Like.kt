package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Like(
	val likeId: UUID,
	val postId: String,
	val userId: String,
	val createdAt: LocalDateTime = LocalDateTime.now()
)
