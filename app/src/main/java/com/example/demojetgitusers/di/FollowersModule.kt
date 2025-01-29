package com.example.demojetgitusers.di

import com.example.demojetgitusers.data.FollowersRepositoryImpl
import com.example.demojetgitusers.data.UserApi
import com.example.demojetgitusers.domain.repository.FollowersRepository
import dagger.Module
import dagger.Provides

@Module
object FollowersModule {
    @Provides
    fun provideFollowersRepository(api: UserApi): FollowersRepository = FollowersRepositoryImpl(api)
}