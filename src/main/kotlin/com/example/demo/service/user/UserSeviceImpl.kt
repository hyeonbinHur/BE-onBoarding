package com.example.demo.service.user

import com.example.demo.domain.User
import com.example.demo.dto.user.request.CreateUserRequest
import com.example.demo.dto.user.response.UserResponse
import com.example.demo.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserSeviceImpl(private val userRepository: UserRepository) : UserService {
	
	@Transactional
	override fun createUser(request: CreateUserRequest): UserResponse {
		val user = User(
			username = request.username,
			email = request.email
		)
		
		val savedUser = userRepository.save(user)
		
		return UserResponse(
			userId = savedUser.userId.toString(),
			username = savedUser.username,
			email = savedUser.email,
			createdAt = savedUser.createdAt.toString()
		)
		
	}
}