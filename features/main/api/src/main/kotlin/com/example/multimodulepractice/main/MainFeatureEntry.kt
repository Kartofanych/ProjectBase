package com.example.multimodulepractice.main

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.multimodulepractice.common.navigation.FeatureEntry

abstract class MainFeatureEntry : FeatureEntry {

    override val featureRoute: String = FEATURE_ROUTE

    override val arguments = listOf(
        navArgument(ARG_LANDMARK_ID) {
            type = NavType.StringType
        }
    )

    protected companion object {
        const val FEATURE_ROUTE = "main"
        const val ARG_LANDMARK_ID = "landmarkId"
    }

}