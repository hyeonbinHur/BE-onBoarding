package com.example.demo.dto.follow.request

import jakarta.validation.constraints.NotBlank

data class UnFollowRequest(
	@field:NotBlank(message = "follow가 확인되지 않았습니다.")
	val followingId: String
)