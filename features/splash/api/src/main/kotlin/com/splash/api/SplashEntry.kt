package com.splash.api

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class SplashEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "splash"
    }
}
