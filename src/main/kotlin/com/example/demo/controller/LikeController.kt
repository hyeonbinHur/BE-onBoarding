package com.example.demo.controller

import com.example.demo.dto.like.request.CreateLikeRequest
import com.example.demo.dto.like.request.DeleteLikeRequest
import com.example.demo.dto.like.response.LikeResponse
import com.example.demo.service.like.LikeService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/likes")
class LikeController(private val likeService: LikeService) {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun likePost(
		@Valid @RequestBody request: CreateLikeRequest
	): LikeResponse {
		return likeService.likePost(request)
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	fun unlikePost(
		@RequestParam postId: String,
		@RequestParam userId: String
	): Boolean {
		return likeService.unlikePost(DeleteLikeRequest(postId, userId))
	}
}