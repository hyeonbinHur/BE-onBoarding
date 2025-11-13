package com.example.demo.controller

import com.example.demo.dto.follow.request.FollowUserRequest
import com.example.demo.dto.follow.request.GetFolloweesRequest
import com.example.demo.dto.follow.request.GetFollowersRequest
import com.example.demo.dto.follow.request.UnFollowRequest
import com.example.demo.dto.follow.response.FollowResponse
import com.example.demo.dto.follow.response.FolloweeDTO
import com.example.demo.dto.follow.response.FollowerDTO
import com.example.demo.service.follow.FollowService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/follow")
class FollowController(private val followService: FollowService) {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun followUser(
		@Valid @RequestBody request: FollowUserRequest
	): FollowResponse {
		return followService.followUser(request)
	}
	
	@GetMapping("/followees")
	@ResponseStatus(HttpStatus.OK)
	fun getFollowingList(@RequestParam userId: String): List<FolloweeDTO> {
		print(GetFolloweesRequest(userId))
		return followService.getFollowees(GetFolloweesRequest(userId))
	}
	
	@GetMapping("/followers")
	@ResponseStatus(HttpStatus.OK)
	fun getFollowerList(@RequestParam userId: String): List<FollowerDTO> {
		return followService.getFollowers(GetFollowersRequest(userId))
	}
	
	@DeleteMapping("/{followingId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun unfollowUser(@PathVariable("followingId") id: String): Boolean {
		return followService.unFollowUser(UnFollowRequest(id))
	}
}