package com.example.demo.dto.common

import java.time.LocalDateTime

data class ApiResponse<T>(
	val data: T,
	val message: String? = null,
	val code: String = "SUCCESS",
	val timestamp: LocalDateTime = LocalDateTime.now()
)