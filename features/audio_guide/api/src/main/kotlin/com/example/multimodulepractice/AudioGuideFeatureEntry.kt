package com.example.multimodulepractice

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class AudioGuideFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "audioguide"
    }
}