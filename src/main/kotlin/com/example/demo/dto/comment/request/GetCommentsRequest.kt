package com.example.demo.dto.comment.request

import jakarta.validation.constraints.NotBlank

data class GetCommentsRequest(
	@field:NotBlank(message = "포스트 아이디가 확인되지 않았습니다.")
	val postId: String
)