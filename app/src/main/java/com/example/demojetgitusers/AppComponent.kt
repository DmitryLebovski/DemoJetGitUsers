package com.example.demojetgitusers

import android.app.Application
import com.example.demojetgitusers.di.AppModule
import dagger.BindsInstance
//import com.example.demojetgitusers.di.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
//    ViewModelModule::class
])
interface AppComponent { // связывает модули и места где они понадобятся, в hilt - AndroidEntryPoint
    fun inject(mainActivity: MainActivity)

    @Component.Factory //даёт возможность создать AppComponent, с переданными зависимостями
    // до создания графа зависимостей
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
        //BindsInstance - передача существующего объекта в граф
        //Binds - указывает какую реализацию использовать для интерфейса без создания объекта
    }

    //испольщуется Factory, а не Builder,
    //тк в Builder для каждого параметра создавалась бы отдельная
    //в Factory можно передать несколько параметров в create, ошибки на уровне компиляции
}