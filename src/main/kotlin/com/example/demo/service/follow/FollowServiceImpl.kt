package com.example.demo.service.follow

import com.example.demo.domain.Follow
import com.example.demo.dto.follow.request.FollowUserRequest
import com.example.demo.dto.follow.request.GetFolloweesRequest
import com.example.demo.dto.follow.request.GetFollowersRequest
import com.example.demo.dto.follow.request.UnFollowRequest
import com.example.demo.dto.follow.response.FollowResponse
import com.example.demo.dto.follow.response.FollowerDTO
import com.example.demo.dto.follow.response.FolloweeDTO

import com.example.demo.repository.follow.FollowRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FollowServiceImpl(private val followRepository: FollowRepository) : FollowService {
	
	@Transactional
	override fun followUser(request: FollowUserRequest): FollowResponse {
		val follow = Follow(
			followerId = request.followerId,
			followeeId = request.followeeId
		)
		
		val savedFollow = followRepository.save(follow)
		
		return FollowResponse(
			followingId = savedFollow.followingId.toString(),
			followerId = savedFollow.followerId.toString(),
			followeeId = savedFollow.followeeId.toString(),
			createdAt = savedFollow.createdAt.toString()
		)
	}
	
	override fun getFollowers(request: GetFollowersRequest): List<FollowerDTO> {
		val followeeId = request.followeeId
		
		return followRepository.findFollowers(followeeId)
	}
	
	override fun getFollowees(request: GetFolloweesRequest): List<FolloweeDTO> {
		val followerId = request.followerId
		
		return followRepository.findFollowees(followerId)
	}
	
	override fun unFollowUser(request: UnFollowRequest): Boolean {
		val followingId = request.followingId
		
		return followRepository.deleteByFollowingId(followingId)
	}
}