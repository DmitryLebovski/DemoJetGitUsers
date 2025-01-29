package com.example.demojetgitusers.presentation.users_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.demojetgitusers.CheckConnection
import com.example.demojetgitusers.R
import com.example.demojetgitusers.UsersState
import com.example.demojetgitusers.reusable_components.UserCard
import com.example.jetgitusers.reusable_components.ShimmerUserList

@Composable
fun UsersScreen(
    viewModel: UsersViewModel,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()


    when (uiState) {
        is UsersState.Loading -> {
            Log.d("UISTATE", uiState.toString())
            ShimmerUserList()
        }
        is UsersState.Success -> {
            val usersList = (uiState as UsersState.Success).users
            Column(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            )  {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    state = lazyListState
                ) {
                    items(usersList) { user ->
                        UserCard(
                            login = user.login,
                            avatarUrl = user.avatar_url,
                            followers = user.followers,
                            onClick = { }
                        )
                    }
                }

                if (!CheckConnection.isInternetAvailable(context)) {
                    Text(
                        text = stringResource(R.string.connection_failed),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        is UsersState.Error -> {
            ErrorScreen()
            Log.d("___UISTATE", (uiState as UsersState.Error).error.toString())
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.connection_failed),
            modifier = Modifier.padding(16.dp)
        )
    }
}