package com.example.demo.service.post

import com.example.demo.dto.post.request.CreatePostRequest
import com.example.demo.dto.post.request.GetPostRequest
import com.example.demo.dto.post.response.PostResponse

interface PostService {
	fun createPost(request: CreatePostRequest): PostResponse
	fun getFollowingPosts(userId: String): List<PostResponse>
	fun getPost(request: GetPostRequest): PostResponse
}