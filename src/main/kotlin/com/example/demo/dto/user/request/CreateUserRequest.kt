package com.example.demo.dto.user.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateUserRequest (
    @field:NotBlank(message = "닉네임은 필수입니다.")
    @field:Size(min = 2, max = 100, message = "닉네임은 2 - 100자 입니다.")
    val username: String,

    @field:NotBlank(message = "이메일 필수입니다.")
    @field:Email(message= "올바른 이메일 형식이 아닙니다")
    val email: String
)