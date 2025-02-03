package com.example.testcore.repository.repositoryImpl

import com.example.testcore.model.User
import com.example.testcore.repository.UserRepository

class UserRepositoryImpl(
    private val api: com.example.testcore.remote.UserApi
): UserRepository {
    override suspend fun getUsers(since: Int): Result<List<User>> {
        return try {
            val response = api.getUsers(sinceId = since)
            if (response.isSuccessful) {
                val users = response.body()?.let { mapDtoToUsers(it) } ?: emptyList()
                val detailedUsers = users.map { user ->
                    getUserInfo(user.login).getOrNull()?.let {
                        user.copy(followers = it.followers)
                    } ?: user
                }
                Result.success(detailedUsers)
            } else {
                val error = response.message()
                Result.failure(Throwable(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserInfo(username: String): Result<User> {
        return try {
            val response = api.getUserInfo(username = username)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                val error =  response.message()

                Result.failure(Throwable(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun mapDtoToUsers(dtoList: List<User>): List<User> {
        return dtoList.map { dto ->
            User(
                login = dto.login,
                id = dto.id,
                avatar_url = dto.avatar_url,
                url = dto.url,
                followers_url = dto.followers_url,
                public_repos = 0,
                followers = 0
            )
        }
    }
}