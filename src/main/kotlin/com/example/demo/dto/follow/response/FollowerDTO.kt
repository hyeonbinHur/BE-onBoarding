package com.example.demo.dto.follow.response

data class FollowerDTO(
	val followingId: String, // follow 취소용
	val createdAt: String, // follow 생성 날짜 -> 이게 필요할까...?
	val followerId: String, // follower id
	val username: String, // follower username
	val email: String, // follower email

	//Todo: 촌수 관련 데이터 필요함
)