package com.example.demojetgitusers.presentation.users_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojetgitusers.components.UsersSubcomponent
import com.example.demojetgitusers.appComponent
import com.example.demojetgitusers.domain.usecase.GetUsersUseCase
import com.example.network.UsersState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    // если указать context тут - пожалуется на утечку памяти, но не приводит к ней
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UsersState>(UsersState.Loading)
    val uiState: StateFlow<UsersState> = _uiState

    init {
        processIntent(UsersIntent.LoadUsers)
    }

    private fun processIntent(intent: UsersIntent) {
        when (intent) {
            is UsersIntent.LoadUsers -> loadUsers()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { UsersState.Loading }

            getUsersUseCase(since = 0)
                .onFailure { throwable -> _uiState.update { UsersState.Error(throwable) }}
                .onSuccess { userList ->
                    _uiState.update {
                        UsersState.Success(users = userList, loadMore = false)
                    }
                }
        }
    }
}

// creators - map, где ключи - классы viewmodel, значения - провайдеры экземпляров этих классов
// если modelClass есть в creators - фабрика использует соответсвующий провайдер
// если modelClass нет в creators - фабрика ищет его суперкласс (isAssignableFrom)
// если нет нигде - ошибка

//class DaggerViewModelFactory @Inject constructor(
//    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = creators[modelClass]
//            ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
//            ?: throw IllegalArgumentException("Unknown model class $modelClass")
//        @Suppress("UNCHECKED_CAST")
//        return creator.get() as T // возвращение нового или уже созданного экземпляра ViewModel
//    }
//}


sealed class UsersIntent {
    data object LoadUsers : UsersIntent()
}

fun Context.createUsersComponent(): UsersSubcomponent {
    return appComponent.usersSubcomponent().create()
}

