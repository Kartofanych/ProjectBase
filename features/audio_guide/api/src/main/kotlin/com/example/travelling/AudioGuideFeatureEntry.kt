package com.example.travelling

import com.example.travelling.common.navigation.FeatureEntry

abstract class AudioGuideFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "audioguide"
    }
}