package com.example.demojetgitusers.domain.usecase

import android.content.Context
import android.widget.Toast
import com.example.demojetgitusers.domain.model.User
import com.example.demojetgitusers.domain.repository.FollowersRepository
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(
    private val context: Context,
    private val followersRepository: FollowersRepository
) {
    suspend operator fun invoke(username: String, page: Int): Result<List<User>> {
        Toast.makeText(context, "${context.theme}", Toast.LENGTH_SHORT).show()
        return followersRepository.getUserFollowers(
            username = username,
            page = page
        )
    }
}