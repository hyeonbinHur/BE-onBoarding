package com.example.demo.repository.like

import com.example.demo.domain.Like

interface LikeRepository {

	fun saveLike(like: Like): Boolean
	fun deleteLike(likeId: String): Boolean
	fun deleteByUserIdAndPostId(userId: String, postId: String): Boolean
	fun existsByUserIdAndPostId(userId: String, postId: String): Boolean
	fun findByLikeId(likeId: String): Like?
}