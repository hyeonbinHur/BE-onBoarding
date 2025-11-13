package com.example.demo.service.user

import com.example.demo.dto.user.request.CreateUserRequest
import com.example.demo.dto.user.response.UserResponse

interface UserService {
    fun createUser(request: CreateUserRequest): UserResponse
}