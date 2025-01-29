package com.example.demojetgitusers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.demojetgitusers.domain.model.User

object CheckConnection {
    fun isInternetAvailable(context: Context): Boolean {
        val result: Boolean

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
        result = when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
        return result
    }
}

sealed class UsersState {
    object Loading : UsersState()
    data class Success(val users: List<User>, val loadMore: Boolean = false) : UsersState()
    data class Error(val error: Throwable) : UsersState()
}
