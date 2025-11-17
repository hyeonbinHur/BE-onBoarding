package com.example.demo.service.post

import com.example.demo.domain.Post
import com.example.demo.dto.post.request.CreatePostRequest
import com.example.demo.dto.follow.request.GetFolloweesRequest
import com.example.demo.dto.follow.request.GetFollowersRequest
import com.example.demo.dto.post.request.GetPostRequest
import com.example.demo.dto.post.response.PostResponse
import com.example.demo.repository.post.PostRepository
import com.example.demo.service.follow.FollowService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
	private val postRepository: PostRepository,
	private val followService: FollowService  // ← FollowService 주입
) : PostService {
	
	@Transactional
	override fun createPost(request: CreatePostRequest): PostResponse {
		val post = Post(
			title = request.title,
			content = request.content,
			authorId = request.authorId,
			likeCount = 0,
			commentCount = 0,
		)
		
		val savedPost = postRepository.save(post)
		
		return PostResponse(
			postId = savedPost.postId.toString(),
			authorId = savedPost.authorId,
			title = savedPost.title,
			createdAt = savedPost.createdAt.toString(),
			likeCount = 0,
			commentCount = 0
		)
	}
	
	override fun getFollowingPosts(userId: String): List<PostResponse> {
		val followers = followService.getFollowers(GetFollowersRequest(userId))
		
		val followeeIds = followers.map { it.followerId }
		
		if (followeeIds.isEmpty()) {
			return emptyList()
		}
		
		val posts = postRepository.findByAuthorIds(followeeIds)
		
		return posts.map { post ->
			PostResponse(
				postId = post.postId.toString(),
				authorId = post.authorId,
				title = post.title,
				createdAt = post.createdAt.toString(),
				likeCount = post.likeCount,
				commentCount = post.commentCount,
			)
		}
	}
	
	override fun getPost(request: GetPostRequest): PostResponse {
		val postId = request.postId
		val post = postRepository.findByPostId(postId)
			?: throw NoSuchElementException("Post not found with id: $postId")
		
		return PostResponse(
			postId = post.postId.toString(),
			authorId = post.authorId,
			title = post.title,
			createdAt = post.createdAt.toString(),
			likeCount = post.likeCount,
			commentCount = post.commentCount,
		)
	}
}