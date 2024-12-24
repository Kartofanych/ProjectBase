package com.promo.api

import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class PromoEntry : FeatureEntry {

    override val featureRoute = BASE_ROUTE

    protected companion object {
        const val BASE_ROUTE = "promo"
    }
}