package com.example.demo.repository.like

import com.example.demo.domain.Like
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.UUID

@Repository
class LikeRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : LikeRepository {

	private val likeRowMapper = RowMapper { rs: ResultSet, _: Int ->
		Like(
			likeId = UUID.fromString(rs.getString("like_id")),
			postId = rs.getString("post_id"),
			userId = rs.getString("user_id"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime()
		)
	}

	override fun saveLike(like: Like): Boolean {
		val sql = """
            INSERT INTO `like` (like_id, post_id, user_id, created_at)
            VALUES (?, ?, ?, ?)
        """.trimIndent()

		return jdbcTemplate.update(
			sql,
			like.likeId.toString(),
			like.postId,
			like.userId,
			like.createdAt
		) > 0
	}

	override fun deleteLike(likeId: String): Boolean {
		val sql = """
			DELETE FROM `like`
			WHERE like_id = ?
		""".trimIndent()
		val deleteRows = jdbcTemplate.update(sql, likeId)
		return deleteRows == 1
	}

	override fun deleteByUserIdAndPostId(userId: String, postId: String): Boolean {
		val sql = """
			DELETE FROM `like`
			WHERE user_id = ? AND post_id = ?
		""".trimIndent()
		val deleteRows = jdbcTemplate.update(sql, userId, postId)
		return deleteRows == 1
	}

	override fun existsByUserIdAndPostId(userId: String, postId: String): Boolean {
		val sql = """
			SELECT COUNT(*) FROM `like`
			WHERE user_id = ? AND post_id = ?
		""".trimIndent()
		val count = jdbcTemplate.queryForObject(sql, Int::class.java, userId, postId)
		return count != null && count > 0
	}

	override fun findByLikeId(likeId: String): Like? {
		val sql = """
			SELECT like_id, post_id, user_id, created_at
			FROM `like`
			WHERE like_id = ?
		""".trimIndent()
		return try {
			jdbcTemplate.queryForObject(sql, likeRowMapper, likeId)
		} catch (e: Exception) {
			null
		}
	}
}