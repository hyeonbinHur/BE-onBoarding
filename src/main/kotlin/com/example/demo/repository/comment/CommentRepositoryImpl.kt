package com.example.demo.repository.comment

import com.example.demo.domain.Comment
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.UUID

@Repository
class CommentRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : CommentRepository {
	
	private val commentRowMapper = RowMapper { rs: ResultSet, _: Int ->
		Comment(
			commentId = UUID.fromString(rs.getString("comment_id")),
			postId = rs.getString("post_id"),
			userId = rs.getString("user_id"),
			content = rs.getString("content"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime()
		)
	}
	
	
	override fun save(comment: Comment): Comment {
		val sql = """
			INSERT INTO comment (comment_id, post_id, user_id, content, created_at)
			VALUES (?, ?, ?, ?, ?)
		""".trimIndent()
		
		jdbcTemplate.update(
			sql,
			comment.commentId.toString(),
			comment.postId,
			comment.userId,
			comment.content,
			comment.createdAt
		)
		return comment
	}
	
	override fun getCommentsByPostId(postId: String): List<Comment> {
		val sql = """
			SELECT comment_id, post_id, user_id, content, created_at
			FROM comment
			WHERE post_id = ?
			ORDER BY created_at DESC
		""".trimIndent()

		return jdbcTemplate.query(sql, commentRowMapper, postId)
	}
}