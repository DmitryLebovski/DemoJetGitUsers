package com.example.demojetgitusers.data

import com.example.demojetgitusers.domain.model.User
import com.example.demojetgitusers.domain.repository.FollowersRepository

class FollowersRepositoryImpl (
    private val api: UserApi
): FollowersRepository {
    override suspend fun getUserFollowers(username: String, page: Int): Result<List<User>> {
        return try {
            val response = api.getUserFollowers(username = username, page = page)
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