package com.example.demo.service.comment

import com.example.demo.dto.comment.request.CreateCommentRequest
import com.example.demo.dto.comment.response.CommentResponse

interface CommentService {
	fun createComment(request: CreateCommentRequest): CommentResponse
	fun getCommentsByPostId(postId: String): List<CommentResponse>
}