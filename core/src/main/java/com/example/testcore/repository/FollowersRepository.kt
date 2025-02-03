package com.example.testcore.repository

import com.example.testcore.model.User

interface FollowersRepository {
    suspend fun getUserFollowers(username: String, page: Int): Result<List<User>>
    suspend fun getUserInfo(username: String): Result<User>
}