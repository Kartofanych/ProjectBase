package com.search_filters.api.data

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class SearchScreenEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "search_screen"
    }
}