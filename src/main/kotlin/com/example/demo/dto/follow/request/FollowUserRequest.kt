package com.example.demo.dto.follow.request

import jakarta.validation.constraints.NotBlank

data class FollowUserRequest(
	@field:NotBlank(message = "Follower가 확인되지 않았습니다")
	val followerId: String,

	@field:NotBlank(message = "Followee가 확인되지 않았습니다")
	val followeeId: String
)
