package com.example.demo.controller

import com.example.demo.dto.post.request.CreatePostRequest
import com.example.demo.dto.post.request.GetPostRequest
import com.example.demo.dto.post.response.PostResponse
import com.example.demo.service.post.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService, service: PostService) {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createPost(
		@Valid @RequestBody request: CreatePostRequest
	): PostResponse {
		return postService.createPost(request)
	}
	
	@GetMapping("/feed")
	@ResponseStatus(HttpStatus.OK)
	fun getFollowingPosts(
		@RequestParam userId: String
	): List<PostResponse> {
		return postService.getFollowingPosts(userId)
	}
	
	
	@GetMapping("/{postId}")
	@ResponseStatus(HttpStatus.OK)
	fun getPost(
		@PathVariable("postId") id: String
	): PostResponse {
		return postService.getPost(GetPostRequest(id))
	}
}