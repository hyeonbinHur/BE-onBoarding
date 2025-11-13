package com.example.demo.controller

import com.example.demo.dto.user.request.CreateUserRequest
import com.example.demo.dto.user.response.UserResponse
import com.example.demo.service.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController (private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(
        @Valid @RequestBody request: CreateUserRequest
    ): UserResponse{
        return userService.createUser(request)
    }
}