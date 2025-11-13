package com.example.demo.dto.follow.response

data class FolloweeDTO(
	val followingId: String, // follow 취소용
	val createdAt: String, // follow 생성 날짜 -> 이게 필요할까...?
	val followeeId: String, // followee id
	val username: String, //followee username
	val email: String, //followee email

	// TODO: 촌수 관련 attribute 필요함
)