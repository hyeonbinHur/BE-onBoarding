package com.example.demo.repository.user

import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID
import User

@Repository
class UserRepositoryImpl(
	private val jdbcTemplate: JdbcTemplate
) : UserRepository {
	
	private val userRowMapper = RowMapper { rs: ResultSet, _: Int ->
		User(
			userId = UUID.fromString(rs.getString("user_id")),
			username = rs.getString("username"),
			email = rs.getString("email"),
			createdAt = rs.getTimestamp("created_at").toLocalDateTime()
		)
	}
	
	override fun save(user: User): User {
		val sql = """
            INSERT INTO user (user_id, username, email, created_at)
            VALUES (?, ?, ?, ?)
        """.trimIndent()
		
		jdbcTemplate.update(
			sql,
			user.userId.toString(),
			user.username,
			user.email,
			user.createdAt
		)
		return user
	}
	
	override fun findById(userId: UUID): User? {
		val sql = "SELECT * FROM users WHERE user_id = ?"
		return try {
			jdbcTemplate.queryForObject(sql, userRowMapper, userId.toString())
		} catch (error: Exception) {
			null
		}
	}
}