package com.example.demojetgitusers.domain.repository

import com.example.demojetgitusers.domain.model.User

interface UserRepository {
    suspend fun getUsers(since: Int): Result<List<User>>
    suspend fun getUserInfo(username: String): Result<User>
}