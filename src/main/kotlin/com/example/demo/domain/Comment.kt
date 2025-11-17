package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Comment(
	val commentId: UUID,
	val postId: String,
	val userId: String,
	val content: String,
	val createdAt: LocalDateTime = LocalDateTime.now()
)
