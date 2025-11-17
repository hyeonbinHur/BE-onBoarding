package com.example.demo.controller

import com.example.demo.dto.comment.request.CreateCommentRequest
import com.example.demo.dto.comment.response.CommentResponse
import com.example.demo.service.comment.CommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/comments")
class CommentController(private val commentService: CommentService) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createComment(
		@Valid @RequestBody request: CreateCommentRequest
	): CommentResponse {
		return commentService.createComment(request)
	}

	@GetMapping("/post/{postId}")
	@ResponseStatus(HttpStatus.OK)
	fun getCommentsByPostId(
		@PathVariable postId: String
	): List<CommentResponse> {
		return commentService.getCommentsByPostId(postId)
	}
}