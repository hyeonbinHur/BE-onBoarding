package com.example.demo.dto.follow.request

import jakarta.validation.constraints.NotBlank

data class GetFolloweesRequest(
	@field:NotBlank(message = "Follower(요청 유저)가 확인되지 않았습니다.")
	val followerId: String,
)
