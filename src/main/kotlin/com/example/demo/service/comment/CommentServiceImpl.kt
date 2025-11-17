package com.example.demo.service.comment

import com.example.demo.domain.Comment
import com.example.demo.dto.comment.request.CreateCommentRequest
import com.example.demo.dto.comment.response.CommentResponse
import com.example.demo.repository.comment.CommentRepository
import com.example.demo.repository.post.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CommentServiceImpl(
	private val commentRepository: CommentRepository,
	private val postRepository: PostRepository
) : CommentService {

	@Transactional
	override fun createComment(request: CreateCommentRequest): CommentResponse {
		val comment = Comment(
			commentId = UUID.randomUUID(),
			postId = request.postId,
			userId = request.userId,
			content = request.content
		)

		val savedComment = commentRepository.save(comment)

		// Post의 commentCount 증가
		postRepository.incrementCommentCount(request.postId)

		return CommentResponse(
			commentId = savedComment.commentId.toString(),
			postId = savedComment.postId,
			userId = savedComment.userId,
			content = savedComment.content,
			createdAt = savedComment.createdAt.toString()
		)
	}

	@Transactional(readOnly = true)
	override fun getCommentsByPostId(postId: String): List<CommentResponse> {
		val comments = commentRepository.getCommentsByPostId(postId)

		return comments.map { comment ->
			CommentResponse(
				commentId = comment.commentId.toString(),
				postId = comment.postId,
				userId = comment.userId,
				content = comment.content,
				createdAt = comment.createdAt.toString()
			)
		}
	}
}