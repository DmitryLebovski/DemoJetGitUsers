package com.example.testcore.repository

import com.example.testcore.model.User


interface UserRepository {
    suspend fun getUsers(since: Int): Result<List<User>>
    suspend fun getUserInfo(username: String): Result<User>
}