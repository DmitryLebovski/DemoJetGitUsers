package com.example.demojetgitusers

import android.app.Application
import android.content.Context
import com.example.demojetgitusers.components.AppComponent
import com.example.demojetgitusers.components.DaggerAppComponent
import com.example.demojetgitusers.components.FollowersSubcomponent

class DemoJetGitApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is DemoJetGitApp -> appComponent
        else -> this.applicationContext.appComponent
    }