package com.search.api

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class SearchEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "search"
    }
}