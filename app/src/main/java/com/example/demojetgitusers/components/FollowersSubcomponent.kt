package com.example.demojetgitusers.components

import com.example.demojetgitusers.di.FollowersModule
import com.example.followers.presentation.FollowersViewModel
import dagger.Subcomponent

@Subcomponent(modules = [FollowersModule::class])
interface FollowersSubcomponent {

    fun followersViewModel(): FollowersViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): FollowersSubcomponent
    }
}