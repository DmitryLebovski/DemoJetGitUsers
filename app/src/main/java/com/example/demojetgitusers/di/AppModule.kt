package com.example.demojetgitusers.di

//import com.example.demojetgitusers.presentation.users_screen.DaggerViewModelFactory
import android.app.Application
import android.content.Context
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
    fun provideUserApi(retrofit: Retrofit): com.example.testcore.remote.UserApi = retrofit.create(
        com.example.testcore.remote.UserApi::class.java)

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context = application.applicationContext
    // application: Application автоматически берется из BindInstance

//
//    @Provides
//    @Singleton
//    fun provideViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory = factory
}

//@Module
//abstract class ViewModelModule {
//    @Target(AnnotationTarget.FUNCTION)
//    @Retention(AnnotationRetention.RUNTIME)
//    @MapKey
//    annotation class ViewModelKey(val value: KClass<out ViewModel>)
//
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(FollowersViewModel::class)
//    abstract fun bindFollowersViewModel(viewModel: FollowersViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(UsersViewModel::class)
//    abstract fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel
//}
//
//@Singleton
//class DaggerViewModelFactory @Inject constructor(
//    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
//            modelClass.isAssignableFrom(it.key)
//        }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")
//        return try {
//            creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//}
