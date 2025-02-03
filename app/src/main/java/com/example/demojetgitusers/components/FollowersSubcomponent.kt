package com.example.demojetgitusers.components

import com.example.demojetgitusers.di.FollowersModule
import com.example.followers.ProviderFollowersViewModel
import dagger.Subcomponent

@Subcomponent(modules = [FollowersModule::class])
interface FollowersSubcomponent: ProviderFollowersViewModel {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FollowersSubcomponent
    }
}