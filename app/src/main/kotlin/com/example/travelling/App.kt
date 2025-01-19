package com.example.travelling

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.example.multimodulepractice.BuildConfig
import com.example.travelling.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class App : Application() {

    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.METRICA_KEY).build()
        AppMetrica.activate(this, config)

        if (isMainProcess()) {
            MapKitFactory.setApiKey(BuildConfig.API_KEY)
            MapKitFactory.initialize(this)
        }

        AppMetrica.reportEvent("App OnCreate")

        appProvider = DaggerAppComponent.factory()
            .create(this)
    }

    private fun isMainProcess(): Boolean {
        val processName = currentProcessName()
        return processName == applicationContext.packageName
    }

    private fun currentProcessName(): String? {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in activityManager.runningAppProcesses) {
            if (processInfo.pid == pid) {
                return processInfo.processName
            }
        }
        return null
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider
