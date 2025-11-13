package com.example.demo.repository.user

import User
import java.util.UUID

interface UserRepository {
    fun save(user: User):User
    fun findById(userId: UUID):User?
}