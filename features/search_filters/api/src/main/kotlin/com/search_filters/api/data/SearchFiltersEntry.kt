package com.search_filters.api.data

import com.example.travelling.common.navigation.FeatureEntry

abstract class SearchFiltersEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "search_filters"
    }
}