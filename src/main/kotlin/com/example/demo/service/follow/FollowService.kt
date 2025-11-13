package com.example.demo.service.follow

import com.example.demo.dto.follow.request.FollowUserRequest
import com.example.demo.dto.follow.request.GetFolloweesRequest
import com.example.demo.dto.follow.request.GetFollowersRequest
import com.example.demo.dto.follow.request.UnFollowRequest
import com.example.demo.dto.follow.request.GetDegreeOfSeparationRequest
import com.example.demo.dto.follow.response.FollowResponse
import com.example.demo.dto.follow.response.FolloweeDTO
import com.example.demo.dto.follow.response.FollowerDTO
import com.example.demo.dto.follow.response.DegreeOfSeparationResponse

//import com.example.demo.dto.common.ApiResponse

interface FollowService {
	fun followUser(request: FollowUserRequest): FollowResponse
	fun getFollowers(request: GetFollowersRequest): List<FollowerDTO>
	fun getFollowees(request: GetFolloweesRequest): List<FolloweeDTO>
	fun unFollowUser(request: UnFollowRequest): Boolean
	fun getDegreeOfSeparation(request: GetDegreeOfSeparationRequest): DegreeOfSeparationResponse
}