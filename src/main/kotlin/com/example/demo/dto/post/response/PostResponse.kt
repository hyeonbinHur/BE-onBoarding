package com.example.demo.dto.post.response

data class PostResponse(
	val postId: String,
	val authorId: String,
	val title: String,
	val likeCount: Int,
	val commentCount: Int,
	val createdAt: String,
)