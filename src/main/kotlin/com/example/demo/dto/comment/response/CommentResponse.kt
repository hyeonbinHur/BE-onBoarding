package com.example.demo.dto.comment.response

data class CommentResponse(
	val commentId: String,
	val postId: String,
	val userId: String,
	val content: String,
	val createdAt: String
)