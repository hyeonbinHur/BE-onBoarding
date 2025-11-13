package com.example.demo.dto.post.request

import jakarta.validation.constraints.NotBlank

data class CreatePostRequest(
	@field:NotBlank(message = "제목은 필수 입니다.")
	val title: String,

	@field:NotBlank(message = "내용은 필수입니다.")
	val content: String,

	@field:NotBlank(message = "작성자가 확인되지 않습니다.")
	val authorId: String,
)