package com.example.demo.service.post

import com.example.demo.domain.Post
import com.example.demo.dto.post.request.CreatePostRequest
import com.example.demo.dto.follow.request.GetFolloweesRequest
import com.example.demo.dto.follow.request.GetFollowersRequest
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
		// 1. FollowService를 사용해서 내가 팔로우하는 사람들 목록 가져오기
		val followers = followService.getFollowers(GetFollowersRequest(userId))
		
		// 2. followee들의 userId 추출
		val followeeIds = followers.map { it.followerId }
		
		// 3. 팔로우하는 사람이 없으면 빈 리스트 반환
		if (followeeIds.isEmpty()) {
			return emptyList()
		}
		
		// 4. 해당 작성자들의 게시물 조회
		val posts = postRepository.findByAuthorIds(followeeIds)
		
		// 5. PostResponse로 변환
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
}