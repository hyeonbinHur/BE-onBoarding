package com.example.demo.repository.post

import com.example.demo.domain.Post
import org.springframework.stereotype.Repository

@Repository
interface PostRepository {
	fun save(post: Post): Post
	fun findByAuthorIds(authorIds: List<String>): List<Post>
}