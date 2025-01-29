package com.example.demojetgitusers.domain.usecase

import android.content.Context
import android.widget.Toast
import com.example.demojetgitusers.domain.model.User
import com.example.demojetgitusers.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val context: Context,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(since: Int): Result<List<User>> {
        Toast.makeText(context, "${context}", Toast.LENGTH_SHORT).show()
        return userRepository.getUsers(since = since)
    }
}