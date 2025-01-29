package com.example.demojetgitusers.components

import com.example.demojetgitusers.di.FollowersModule
import com.example.demojetgitusers.presentation.followers_screen.FollowersViewModel
import dagger.Subcomponent

@Subcomponent(modules = [FollowersModule::class])
interface FollowersSubcomponent {

    fun followersViewModel(): FollowersViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): FollowersSubcomponent
    }
}