package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Post(
	val postId: UUID = UUID.randomUUID(),
	val authorId: String,
	val title: String,
	val content: String,
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val likeCount: Int,
	val commentCount: Int,
	
	)
