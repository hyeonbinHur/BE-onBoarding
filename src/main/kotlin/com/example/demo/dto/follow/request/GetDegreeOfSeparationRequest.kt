package com.example.demo.dto.follow.request

import jakarta.validation.constraints.NotBlank

data class GetDegreeOfSeparationRequest(
	@field:NotBlank(message = "시작 유저가 확인되지 않았습니다.")
	val fromUserId: String,

	@field:NotBlank(message = "대상 유저가 확인되지 않았습니다.")
	val toUserId: String
)