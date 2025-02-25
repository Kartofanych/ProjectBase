package com.search.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class SearchFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "main/search"
    }
}