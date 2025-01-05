package com.example.travelling

import android.app.Application
import com.example.multimodulepractice.BuildConfig
import com.example.travelling.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.MapKitFactory.setApiKey

class App : Application() {

    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()
        setApiKey(BuildConfig.API_KEY)
        MapKitFactory.initialize(this)

        appProvider = DaggerAppComponent.factory()
            .create(this)
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider
