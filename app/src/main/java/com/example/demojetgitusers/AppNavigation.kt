package com.example.demojetgitusers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.demojetgitusers.Routes.FOLLOWERS_SCREEN
import com.example.demojetgitusers.Routes.USERS_SCREEN
import com.example.demojetgitusers.presentation.followers_screen.FollowersScreen
import com.example.demojetgitusers.presentation.users_screen.UsersScreen

object Routes {
    const val USERS_SCREEN = "UsersScreen"
    const val FOLLOWERS_SCREEN = "FollowersScreen"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = USERS_SCREEN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(USERS_SCREEN) {
            UsersScreen(
                navController = navController
            )
        }

        composable(
            route = "$FOLLOWERS_SCREEN/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: return@composable
            FollowersScreen(
                username = username,
                navigateToFollowers = { nextUser ->
                    navController.navigate("$FOLLOWERS_SCREEN/$nextUser")
                },
                popBackStack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
