package com.example.demo.dto.like.request

import jakarta.validation.constraints.NotBlank

data class CreateLikeRequest(
	@field:NotBlank(message = "좋아요를 추가할 포스트가 확인되지 않았습니다.")
	val postId: String,
	
	@field:NotBlank(message = "좋아요를 추가할 유저가 확인되지 않았습니다.")
	val userId: String
)