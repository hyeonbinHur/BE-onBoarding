package com.example.demo.service.like

import com.example.demo.domain.Like
import com.example.demo.dto.like.request.CreateLikeRequest
import com.example.demo.dto.like.request.DeleteLikeRequest
import com.example.demo.dto.like.response.LikeResponse
import com.example.demo.repository.like.LikeRepository
import com.example.demo.repository.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class LikeServiceImpl(
	private val likeRepository: LikeRepository,
	private val postRepository: PostRepository
) : LikeService {

	@Transactional
	override fun likePost(request: CreateLikeRequest): LikeResponse {
		// 중복 좋아요 체크
		if (likeRepository.existsByUserIdAndPostId(request.userId, request.postId)) {
			throw IllegalStateException("이미 좋아요를 누른 게시물입니다.")
		}

		val like = Like(
			likeId = UUID.randomUUID(),
			postId = request.postId,
			userId = request.userId
		)

		val success = likeRepository.saveLike(like)
		if (!success) {
			throw IllegalStateException("좋아요 저장에 실패했습니다.")
		}

		// Post의 likeCount 증가
		postRepository.incrementLikeCount(request.postId)

		return LikeResponse(
			likeId = like.likeId.toString(),
			postId = like.postId,
			userId = like.userId,
			createdAt = like.createdAt.toString()
		)
	}

	@Transactional
	override fun unlikePost(request: DeleteLikeRequest): Boolean {
		// 좋아요 존재 여부 체크
		if (!likeRepository.existsByUserIdAndPostId(request.userId, request.postId)) {
			throw NoSuchElementException("좋아요를 찾을 수 없습니다.")
		}

		val success = likeRepository.deleteByUserIdAndPostId(request.userId, request.postId)
		if (success) {
			// Post의 likeCount 감소
			postRepository.decrementLikeCount(request.postId)
		}
		return success
	}
}