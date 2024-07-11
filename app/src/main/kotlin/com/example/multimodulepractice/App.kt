package com.example.multimodulepractice

import android.app.Application
import com.example.multimodulepractice.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.MapKitFactory.setApiKey

class App : Application() {

    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()
        //TODO remove key from git
        setApiKey("2799c068-e03b-4ff4-908a-7802c28709e5")
        MapKitFactory.initialize(this)

        appProvider = DaggerAppComponent.factory()
            .create(this)

        appProvider.geoManager.start()
    }

}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider
