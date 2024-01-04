package com.example.multimodulepractice

import android.app.Application
import com.example.common.di.ApplicationProvider
import com.example.multimodulepractice.di.AppProvider
import com.example.multimodulepractice.di.DaggerAppComponent

class App : Application(), ApplicationProvider {

    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        appProvider = DaggerAppComponent.builder()
            .build()
    }

    override fun mainProvider(): Any = appProvider
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider