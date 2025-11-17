package com.example.demo.repository.comment

import com.example.demo.domain.Comment

interface CommentRepository {
	fun save(comment: Comment): Comment
	fun getCommentsByPostId(postId: String): List<Comment>
	
}