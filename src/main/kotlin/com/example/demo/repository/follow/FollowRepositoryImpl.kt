package com.example.demo.repository.follow

import com.example.demo.domain.Follow
import com.example.demo.dto.follow.response.FolloweeDTO
import com.example.demo.dto.follow.response.FollowerDTO
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.UUID

@Repository
class FollowRepositoryImpl(private val jdbcTemplate: JdbcTemplate) : FollowRepository {
	
	private val followRowMapper = RowMapper { rs: ResultSet, _: Int ->
		Follow(
			followingId = UUID.fromString(rs.getString("following_id")),
			followerId = rs.getString("follower_id"),
			followeeId = rs.getString("followee_id"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime()
		)
	}
	
	private val followeeDTORowMapper = RowMapper { rs: ResultSet, _: Int ->
		FolloweeDTO(
			followingId = rs.getString("following_id"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime().toString(), // LocalDateTime을 String으로
			followeeId = rs.getString("followee_id"),
			username = rs.getString("username"),
			email = rs.getString("email")
		)
	}
	
	private val followerDTORowMapper = RowMapper { rs: ResultSet, _: Int ->
		FollowerDTO(
			followingId = rs.getString("following_id"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime().toString(),
			followerId = rs.getString("follower_id"),
			username = rs.getString("username"),
			email = rs.getString("email")
		)
	}
	
	override fun save(follow: Follow): Follow {
		val sql = """
				INSERT INTO follow (following_id, follower_id, followee_id) VALUES (?, ?, ?)
			""".trimIndent()
		jdbcTemplate.update(
			sql,
			follow.followingId.toString(),
			follow.followerId.toString(),
			follow.followeeId.toString()
		)
		
		return follow
	}
	
	override fun findFollowees(followerId: String): List<FolloweeDTO> {
		val sql = """
        SELECT
            f.following_id,
            f.followee_id,
            f.created_at,
            u.user_id,
            u.username,
            u.email
        FROM follow f
        INNER JOIN user u ON f.followee_id = u.user_id
        WHERE f.follower_id = ?
        ORDER BY f.created_at DESC
       """.trimIndent()
		
		return jdbcTemplate.query(sql, followeeDTORowMapper, followerId)
	}
	
	override fun findFollowers(followeeId: String): List<FollowerDTO> {
		val sql = """
			SELECT
				f.following_id,
				f.follower_id,
				f.created_at,
				u.user_id,
				u.username,
				u.email
			FROM follow f
			INNER JOIN user u ON f.follower_id = u.user_id
			WHERE f.followee_id = ?
			ORDER BY f.created_at DESC
		""".trimIndent()
		
		return jdbcTemplate.query(sql, followerDTORowMapper, followeeId)
	}
	
	override fun deleteByFollowingId(followingId: String): Boolean {
		val sql = """
			DELETE FROM follow
			WHERE following_id = ?
		""".trimIndent()
		val deleteRows = jdbcTemplate.update(sql, followingId)

		return deleteRows == 1
	}

	override fun findDegreeOfSeparation(fromUserId: String, toUserId: String): Int? {
		val sql = """
			WITH RECURSIVE follow_chain AS (
				-- Base case: 직접 팔로우 (1촌)
				SELECT
					followee_id as user_id,
					1 as degree
				FROM follow
				WHERE follower_id = ?

				UNION ALL

				-- Recursive case: 친구의 친구 (2촌, 3촌)
				SELECT
					f.followee_id as user_id,
					fc.degree + 1 as degree
				FROM follow f
				INNER JOIN follow_chain fc ON f.follower_id = fc.user_id
				WHERE fc.degree < 3
			)
			SELECT MIN(degree) as degree
			FROM follow_chain
			WHERE user_id = ?
		""".trimIndent()

		return try {
			jdbcTemplate.queryForObject(sql, Int::class.java, fromUserId, toUserId)
		} catch (e: Exception) {
			null
		}
	}
}
