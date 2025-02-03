package com.example.demojetgitusers.di

import dagger.Module
import dagger.Provides

@Module
object FollowersModule {
    @Provides
    fun provideFollowersRepository(api: com.example.testcore.remote.UserApi): com.example.testcore.repository.FollowersRepository =
        com.example.testcore.repository.repositoryImpl.FollowersRepositoryImpl(api)
}