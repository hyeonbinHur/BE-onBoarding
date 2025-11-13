package com.example.demo.dto.follow.response

data class DegreeOfSeparationResponse(
	val fromUserId: String,
	val toUserId: String,
	val degree: Int  // -1이면 관계 없음, 0이면 본인, 1~3이면 해당 촌수
)