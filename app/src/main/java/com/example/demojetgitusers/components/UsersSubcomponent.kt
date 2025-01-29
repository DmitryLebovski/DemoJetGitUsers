package com.example.demojetgitusers.components

import com.example.demojetgitusers.di.UsersModule
import com.example.demojetgitusers.presentation.users_screen.UsersViewModel
import dagger.Subcomponent

@Subcomponent(modules = [UsersModule::class])
// подкомпонентны не могут иметь ту же область что и родительский компонент
interface UsersSubcomponent {

    fun usersViewModel(): UsersViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): UsersSubcomponent
    }
}