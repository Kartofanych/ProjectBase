package com.inno.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_LIST_ROUTE
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_MAP_ROUTE
import com.inno.impl.MainFeatureImpl.Companion.SCREEN_PROFILE_ROUTE
import com.inno.impl.ui.list.ListScreen
import com.inno.impl.ui.map.MapScreen
import com.inno.impl.ui.map.MapScreenEventHandler
import com.inno.impl.ui.map.MapViewModel
import com.inno.impl.ui.profile.ProfileScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    val viewModel: MapViewModel = hiltViewModel()

    Box(modifier = Modifier.padding(bottom = 40.dp)) {
        NavHost(
            navController = navController,
            startDestination = SCREEN_MAP_ROUTE
        ) {
            composable(route = SCREEN_MAP_ROUTE) {

                MapScreenEventHandler(
                    uiEvent = viewModel.uiEvent
                )

                MapScreen(
                    viewModel.uiStateFlow.collectAsState().value,
                    viewModel::onMapAction,
                    viewModel.map
                )
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