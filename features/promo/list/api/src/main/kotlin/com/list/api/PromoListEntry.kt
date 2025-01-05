package com.list.api

import com.example.travelling.common.navigation.FeatureEntry

abstract class PromoListEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "promo/list"
    }
}
