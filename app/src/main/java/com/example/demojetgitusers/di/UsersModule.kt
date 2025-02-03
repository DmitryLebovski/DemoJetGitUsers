package com.example.demojetgitusers.di

import dagger.Module
import dagger.Provides

@Module
object UsersModule {
    @Provides
    fun provideUserRepository(api: com.example.testcore.remote.UserApi): com.example.testcore.repository.UserRepository =
        com.example.testcore.repository.repositoryImpl.UserRepositoryImpl(api)
}