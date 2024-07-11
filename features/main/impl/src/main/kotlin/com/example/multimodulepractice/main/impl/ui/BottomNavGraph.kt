package com.example.multimodulepractice.main.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.multimodulepractice.main.impl.MainFeatureImpl.Companion.SCREEN_LIST_ROUTE
import com.example.multimodulepractice.main.impl.MainFeatureImpl.Companion.SCREEN_MAP_ROUTE
import com.example.multimodulepractice.main.impl.MainFeatureImpl.Companion.SCREEN_PROFILE_ROUTE
import com.example.multimodulepractice.main.impl.ui.list.ListScreen
import com.example.multimodulepractice.main.impl.ui.list.ListViewModel
import com.example.multimodulepractice.main.impl.ui.map.MapScreen
import com.example.multimodulepractice.main.impl.ui.map.MapScreenEventHandler
import com.example.multimodulepractice.main.impl.ui.map.MapViewModel
import com.example.multimodulepractice.main.impl.ui.profile.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    mapViewModel: MapViewModel,
    listViewModel: ListViewModel
) {

    Box(modifier = Modifier.padding(bottom = 40.dp)) {
        NavHost(
            navController = navController,
            startDestination = SCREEN_MAP_ROUTE
        ) {
            composable(route = SCREEN_MAP_ROUTE) {

                MapScreenEventHandler(
                    uiEvent = mapViewModel.uiEvent
                )

                MapScreen(
                    mapViewModel.uiStateFlow.collectAsState().value,
                    mapViewModel::onMapAction,
                    mapViewModel.map
                )
            }

            composable(route = SCREEN_LIST_ROUTE) {
                ListScreen(
                    listViewModel.uiStateFlow.collectAsState().value,
                    listViewModel::onListAction
                )
            }

            composable(route = SCREEN_PROFILE_ROUTE) {
                ProfileScreen()
            }
        }
    }
}