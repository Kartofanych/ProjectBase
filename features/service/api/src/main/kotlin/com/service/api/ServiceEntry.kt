package com.service.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.travelling.common.navigation.FeatureEntry

abstract class ServiceEntry : FeatureEntry {

    override val featureRoute = "$BASE_ROUTE/{$ARG_SERVICE_ID}"

    override val arguments = listOf(
        navArgument(ARG_SERVICE_ID) {
            type = NavType.StringType
        }
    )

    fun destination(serviceId: String): String =
        "$BASE_ROUTE/$serviceId"

    protected companion object {
        const val BASE_ROUTE = "service"
        const val ARG_SERVICE_ID = "landmarkId"
    }
}