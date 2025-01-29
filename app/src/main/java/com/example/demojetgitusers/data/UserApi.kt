package com.example.demojetgitusers.data

import com.example.demojetgitusers.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("/users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int = 20,
        @Header("Authorization") authorization: String = "Bearer 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",
        @Query("since") sinceId: Int = 0
    ): Response<List<User>>

    @GET("/users/{username}")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String = "Bearer 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",
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Path("username") username: String
    ): Response<User>

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Header("Accept") accept: String = "application/vnd.github+json",
        @Path("username") username: String,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
    ): Response<List<User>>
}