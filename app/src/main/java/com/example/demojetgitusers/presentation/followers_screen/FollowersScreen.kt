package com.example.demojetgitusers.presentation.followers_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demojetgitusers.DemoJetGitApp
import com.example.demojetgitusers.R
import com.example.demojetgitusers.UsersState
import com.example.demojetgitusers.appComponent
import com.example.demojetgitusers.createFollowersComponent
import com.example.demojetgitusers.presentation.users_screen.ErrorScreen
import com.example.demojetgitusers.presentation.users_screen.UsersViewModel
import com.example.demojetgitusers.reusable_components.UserCard
import com.example.jetgitusers.reusable_components.ShimmerUserList
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersScreen(
    username: String,
    popBackStack: () -> Unit,
    navigateToFollowers: (String) -> Unit,
//    viewModel: FollowersViewModel
){
    val context = LocalContext.current

    val followersComponent = remember { context.createFollowersComponent() }
    val viewModel = remember { followersComponent.followersViewModel() }

//    val factory = (LocalContext.current.applicationContext as DemoJetGitApp).appComponent.getViewModelFactory()
//    val viewModel: FollowersViewModel = viewModel(factory = factory)

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        delay(100L)
        viewModel.getUserFollowers(
            page = 1,
            username = username
        )
    }

    val lazyListState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    when(uiState) {
        is UsersState.Loading -> {
            ShimmerUserList()
        }

        is UsersState.Success -> {
            val followersList = (uiState as UsersState.Success).users

            Scaffold (
                topBar =  {
                    TopAppBar(
                        title = {
                            Text(text = username)
                        },
                        navigationIcon = {
                            IconButton(onClick = { popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.back)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                        ),
                        scrollBehavior = scrollBehavior
                    )
                },
                content = { paddingValues ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = paddingValues.calculateTopPadding() + 8.dp)
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        state = lazyListState
                    ) {
                        items(followersList) { user ->
                            UserCard(
                                login = user.login,
                                avatarUrl = user.avatar_url,
                                followers = user.followers,
                                onClick = {
                                    navigateToFollowers(user.login)
                                }
                            )
                        }
                    }
                }
            )
        }

        is UsersState.Error -> {
            ErrorScreen()
            Log.d("UISTATE", (uiState as UsersState.Error).error.toString())
        }
    }
}
