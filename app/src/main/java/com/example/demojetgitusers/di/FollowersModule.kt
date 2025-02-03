package com.example.demojetgitusers.di

import com.example.testcore.remote.UserApi
import com.example.testcore.repository.FollowersRepository
import com.example.testcore.repository.repositoryImpl.FollowersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object FollowersModule {
    @Provides
    fun provideFollowersRepository(api: UserApi): FollowersRepository =
        FollowersRepositoryImpl(api)
}