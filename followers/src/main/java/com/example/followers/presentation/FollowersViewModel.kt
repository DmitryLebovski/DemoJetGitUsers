package com.example.followers.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.followers.domain.usecase.GetFollowersUseCase
import com.example.network.UsersState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowersViewModel @Inject constructor(
    private val getUserFollowersUseCase: GetFollowersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersState>(UsersState.Loading)
    val uiState: StateFlow<UsersState> = _uiState

    fun getUserFollowers(page: Int, username: String) {
        viewModelScope.launch {
            _uiState.update { UsersState.Loading }
            getUserFollowersUseCase(
                username = username,
                page = page
            )
                .onFailure { throwable ->
                    _uiState.update { UsersState.Error(throwable) }
                }
                .onSuccess { followerList ->
                    Log.d("FOLLOWERS_ID", followerList.map { it.id }.toString())
                    _uiState.update { UsersState.Success(users = followerList) }
                }
        }
    }
}