package com.reviews.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class ReviewsEntry : FeatureEntry {

    override val featureRoute = "$BASE_ROUTE/{$ARG_OBJECT_ID}&{$ARG_OBJECT_TYPE}"

    override val arguments = listOf(
        navArgument(ARG_OBJECT_ID) {
            type = NavType.StringType
        },
        navArgument(ARG_OBJECT_TYPE) {
            type = NavType.StringType
        }
    )

    companion object {
        const val BASE_ROUTE = "reviews"
        const val ARG_OBJECT_ID = "id"
        const val ARG_OBJECT_TYPE = "type"

        fun destination(id: String, objectType: String): String =
            "$BASE_ROUTE/$id&$objectType"
    }
}
