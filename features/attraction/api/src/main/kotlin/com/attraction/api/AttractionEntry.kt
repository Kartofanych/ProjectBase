package com.attraction.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class AttractionEntry : FeatureEntry {

    override val featureRoute = "$BASE_ROUTE/{$ARG_ATTRACTION_ID}"

    override val arguments = listOf(
        navArgument(ARG_ATTRACTION_ID) {
            type = NavType.StringType
        }
    )

    fun destination(serviceId: String): String =
        "$BASE_ROUTE/$serviceId"

    protected companion object {
        const val BASE_ROUTE = "attraction"
        const val ARG_ATTRACTION_ID = "id"
    }
}