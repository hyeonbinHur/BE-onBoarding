package com.example.demo.dto.post.request

import jakarta.validation.constraints.NotBlank

data class GetPostRequest(
	@field:NotBlank(message = "postId가 확인되지 않았습니다.")
	val postId: String
)