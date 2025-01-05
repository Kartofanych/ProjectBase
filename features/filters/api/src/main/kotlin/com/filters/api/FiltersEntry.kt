package com.filters.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class FiltersEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "filters"
    }
}