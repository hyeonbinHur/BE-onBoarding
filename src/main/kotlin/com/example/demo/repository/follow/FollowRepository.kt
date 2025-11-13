package com.example.demo.repository.follow

import com.example.demo.domain.Follow
import com.example.demo.dto.follow.response.FolloweeDTO
import com.example.demo.dto.follow.response.FollowerDTO

interface FollowRepository {
	fun save(follow: Follow): Follow
	fun findFollowees(followerId: String): List<FolloweeDTO>
	fun findFollowers(followeeId: String): List<FollowerDTO>
	fun deleteByFollowingId(followingId: String): Boolean
	
}