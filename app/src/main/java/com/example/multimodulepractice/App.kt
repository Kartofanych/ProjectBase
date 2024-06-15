package com.example.multimodulepractice

import android.app.Application
import com.example.multimodulepractice.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.MapKitFactory.setApiKey
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO remove key from git
        setApiKey("2799c068-e03b-4ff4-908a-7802c28709e5")
        MapKitFactory.initialize(this)

        DaggerAppComponent.factory()
            .create(this)
    }

}
