package com.example.multimodulepractice.main.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.example.multimodulepractice.main.impl.ui.profile.ProfileScreenEventHandler
import com.example.multimodulepractice.main.impl.ui.profile.ProfileViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    mapViewModel: MapViewModel,
    listViewModel: ListViewModel,
    profileViewModel: ProfileViewModel,
    navigateToLogin: () -> Unit,
    openFilters: () -> Unit
) {

    Box(modifier = Modifier.padding(bottom = 40.dp)) {
        NavHost(
            navController = navController,
            startDestination = SCREEN_MAP_ROUTE
        ) {
            composable(route = SCREEN_MAP_ROUTE) {
                MapScreenEventHandler(
                    uiEvent = mapViewModel.uiEvent,
                    openFilters = openFilters
                )

                MapScreen(
                    mapViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    mapViewModel::onMapAction,
                    mapViewModel.map
                )
            }

            composable(route = SCREEN_LIST_ROUTE) {
                ListScreen(
                    listViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    listViewModel::onListAction
                )
            }

            composable(route = SCREEN_PROFILE_ROUTE) {
                ProfileScreenEventHandler(
                    navigateToLogin = navigateToLogin,
                    uiEvent = profileViewModel.uiEvent
                )

                ProfileScreen(
                    profileViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    profileViewModel::onProfileAction
                )
            }
        }
    }
}