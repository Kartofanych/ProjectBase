package com.example.multimodulepractice.common.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.register(
    featureEntry: FeatureEntry,
    navController: NavController,
    mainNavController: NavController = navController,
    modifier: Modifier = Modifier
) {
    featureEntry.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        mainNavController = mainNavController,
        modifier = modifier
    )
}
