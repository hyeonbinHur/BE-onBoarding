package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Post(
	val postId: UUID = UUID.randomUUID(),
	val authorId: String, // 코드레벨 오류, UUID로 타입 변경해야합니다.
	val title: String,
	val content: String,
	val createdAt: LocalDateTime = LocalDateTime.now(),
	val likeCount: Int,
	val commentCount: Int,
)
