package com.main_map.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class MapFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "main/map"
    }
}