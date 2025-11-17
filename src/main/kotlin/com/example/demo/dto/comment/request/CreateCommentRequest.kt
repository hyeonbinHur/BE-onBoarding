package com.example.demo.dto.comment.request

import jakarta.validation.constraints.NotBlank
import java.util.UUID

data class CreateCommentRequest(
	@field:NotBlank(message = "댓글을 생성할 포스트가 확인되지 않았습니다.")
	val postId: String,
	
	@field:NotBlank(message = "댓글 생성자가 확인되지 않았습니다.")
	val userId: String,
	
	@field:NotBlank(message = "댓글 내용이 확인되지 않았습니다.")
	val content: String,
)
