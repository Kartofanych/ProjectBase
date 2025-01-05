package com.splash.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class LaunchEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "splash"
    }
}
