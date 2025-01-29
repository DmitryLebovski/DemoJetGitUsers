package com.example.demojetgitusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demojetgitusers.presentation.users_screen.UsersScreen
import com.example.demojetgitusers.presentation.users_screen.UsersViewModel
import com.example.demojetgitusers.ui.theme.DemoJetGitUsersTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    //lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var usersViewModel: UsersViewModel

    //private val usersViewModel: UsersViewModel by viewModels { viewModelFactory } // аналог вещи ниже


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this) // создание всех необходимых зависимостей

         //usersViewModel = ViewModelProvider(this, viewModelFactory)[UsersViewModel::class.java]
        // viewModelProvider управляет созданием и хранением viewModel экземпляров,
        // конкретно тут проверяет есть ли уже созданный экземпляр


        enableEdgeToEdge()
        setContent {
            DemoJetGitUsersTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp)
                ) {
                    UsersScreen(usersViewModel)
                }
            }
        }
    }
}
