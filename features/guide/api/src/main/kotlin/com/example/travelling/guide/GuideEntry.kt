package com.example.travelling.guide

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.travelling.common.navigation.FeatureEntry

abstract class GuideEntry : FeatureEntry {

    override val featureRoute = "$BASE_ROUTE/{$ARG_LANDMARK_ID}"

    override val arguments = listOf(
        navArgument(ARG_LANDMARK_ID) {
            type = NavType.StringType
        }
    )

    fun destination(landmarkId: String): String =
        "$BASE_ROUTE/$landmarkId"

    protected companion object {
        const val BASE_ROUTE = "guide"
        const val ARG_LANDMARK_ID = "landmarkId"
    }

}