package com.example.demo.dto.post.response

data class FeedResponse(
	val totalCount: Int,
	val posts: List<PostResponse>
)