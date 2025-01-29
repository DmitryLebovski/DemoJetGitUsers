package com.example.demojetgitusers.di

//import com.example.demojetgitusers.presentation.users_screen.DaggerViewModelFactory
import android.app.Application
import android.content.Context
import com.example.demojetgitusers.data.FollowersRepositoryImpl
import com.example.demojetgitusers.data.UserApi
import com.example.demojetgitusers.data.UserRepositoryImpl
import com.example.demojetgitusers.domain.repository.FollowersRepository
import com.example.demojetgitusers.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideFollowersRepository(api: UserApi): FollowersRepository = FollowersRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application.applicationContext
    // application: Application автоматически берется из BindInstance

}
//
//@Module
//abstract class ViewModelModule { //кастомная аннотация для хранения map в привязке к viewmodel
//    @MustBeDocumented
//    @Target(AnnotationTarget.FUNCTION)
//    @Retention(AnnotationRetention.RUNTIME)
//    @MapKey
//    annotation class ViewModelKey(val value: KClass<out ViewModel>) //метаинформация о классе
//
//    @Binds
//    @IntoMap // указывает dagger, что эта view model должна быть добавлена в map
//    @ViewModelKey(UsersViewModel::class)
//    abstract fun bindUsersViewModel(usersViewModel: UsersViewModel): ViewModel
//
//    @Binds // при запросе к ViewModelProvider.Factory - предоставить экземпляр DaggerViewModelFActory
//    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
//}