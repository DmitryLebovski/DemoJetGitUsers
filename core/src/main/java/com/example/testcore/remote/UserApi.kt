package com.example.testcore.remote

import com.example.testcore.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("/users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int = 20,
        @Header("Authorization") authorization: String = "Bearer",
        @Query("since") sinceId: Int = 0
    ): Response<List<User>>

    @GET("/users/{username}")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String = "Bearer",
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