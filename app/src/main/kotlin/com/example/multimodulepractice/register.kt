package com.example.multimodulepractice

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.common.navigation.FeatureEntry

fun NavGraphBuilder.register(
    featureEntry: FeatureEntry,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    featureEntry.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}
