package com.example.demo.domain

import java.time.LocalDateTime
import java.util.UUID

data class Follow(
	val followingId: UUID = UUID.randomUUID(),
	val followerId: String, // 코드레벨 오류, UUID로 타입 변경해야합니다.
	val followeeId: String, // 코드레벨 오류, UUID로 타입 변경해야합니다.
	val createdAt: LocalDateTime = LocalDateTime.now()
)
