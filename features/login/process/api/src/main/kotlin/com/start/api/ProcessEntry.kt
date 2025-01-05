package com.start.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class ProcessEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "login/process"
    }
}
