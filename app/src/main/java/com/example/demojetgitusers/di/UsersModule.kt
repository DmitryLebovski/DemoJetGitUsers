package com.example.demojetgitusers.di

import com.example.demojetgitusers.data.UserApi
import com.example.demojetgitusers.data.UserRepositoryImpl
import com.example.demojetgitusers.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
object UsersModule {
    @Provides
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImpl(api)
}