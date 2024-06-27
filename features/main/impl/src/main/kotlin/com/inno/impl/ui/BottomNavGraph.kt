package com.inno.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_LIST_ROUTE
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_MAP_ROUTE
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_PROFILE_ROUTE
import com.inno.impl.ui.list.ListScreen
import com.inno.impl.ui.map.MapScreen
import com.inno.impl.ui.map.MapViewModel
import com.inno.impl.ui.profile.ProfileScreen


@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier) {
    val viewModel: MapViewModel = hiltViewModel()
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = SCREEN_MAP_ROUTE
        ) {
            composable(route = SCREEN_MAP_ROUTE) {
                MapScreen(viewModel)
            }

            composable(route = SCREEN_LIST_ROUTE) {
                ListScreen()
            }

            composable(route = SCREEN_PROFILE_ROUTE) {
                ProfileScreen()
            }
        }
    }
}