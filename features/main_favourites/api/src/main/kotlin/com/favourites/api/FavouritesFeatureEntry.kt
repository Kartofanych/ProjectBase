package com.favourites.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class FavouritesFeatureEntry : FeatureEntry {

    override val featureRoute: String = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "main/favourites"
    }
}