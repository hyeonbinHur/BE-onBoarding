package com.example.demo.dto.follow.response

data class FollowResponse(
	val followingId: String,
	val followerId: String,
	val followeeId: String,
	val createdAt: String
)