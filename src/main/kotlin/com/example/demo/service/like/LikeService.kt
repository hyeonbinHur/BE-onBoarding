package com.example.demo.service.like

import com.example.demo.dto.like.request.CreateLikeRequest
import com.example.demo.dto.like.request.DeleteLikeRequest
import com.example.demo.dto.like.response.LikeResponse

interface LikeService {
	fun likePost(request: CreateLikeRequest): LikeResponse
	fun unlikePost(request: DeleteLikeRequest): Boolean
}