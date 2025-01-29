package com.example.demojetgitusers

import android.app.Application
import android.content.Context

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