package com.example.demo.repository.post

import com.example.demo.domain.Post
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.UUID

@Repository
class PostRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : PostRepository {
	
	private val postRowMapper = RowMapper { rs: ResultSet, _: Int ->
		Post(
			postId = UUID.fromString(rs.getString("post_id")),
			authorId = rs.getString("author_id"),
			title = rs.getString("title"),
			content = rs.getString("content"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime(),
			likeCount = rs.getInt("likes_count"),
			commentCount = rs.getInt("comments_count")
		)
	}
	
	override fun save(post: Post): Post {
		val sql = """
			INSERT INTO post (post_id, author_id, title, content, likes_count,comments_count, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
		""".trimIndent()
		
		jdbcTemplate.update(
			sql,
			post.postId.toString(),
			post.authorId,
			post.title,
			post.content,
			0,
			0,
			post.createdAt
		)
		
		return post
	}
	
	override fun findByAuthorIds(authorIds: List<String>): List<Post> {
		if (authorIds.isEmpty()) {
			return emptyList()
		}
		
		val placeholders = authorIds.joinToString(",") { "?" }
		val sql = """
			
			SELECT post_id, author_id, title, content, created_at, likes_count, comments_count
			FROM post
			WHERE author_id IN ($placeholders)
			ORDER BY created_at DESC
		""".trimIndent()
		
		return jdbcTemplate.query(sql, postRowMapper, *authorIds.toTypedArray())
	}
}