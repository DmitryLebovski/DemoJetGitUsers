package com.example.demojetgitusers.domain.repository

import com.example.demojetgitusers.domain.model.User

interface FollowersRepository {
    suspend fun getUserFollowers(username: String, page: Int): Result<List<User>>
    suspend fun getUserInfo(username: String): Result<User>
}