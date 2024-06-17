package com.example.multimodulepractice

import android.app.Application
import com.example.multimodulepractice.di.DaggerAppComponent
import com.inno.geo.GeoManager
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.MapKitFactory.setApiKey
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var geoManager: GeoManager

    override fun onCreate() {
        super.onCreate()
        //TODO remove key from git
        setApiKey("2799c068-e03b-4ff4-908a-7802c28709e5")
        MapKitFactory.initialize(this)

        DaggerAppComponent.factory()
            .create(this)

        geoManager.start()
    }

}
