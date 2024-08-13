package com.example.multimodulepractice.main.impl.ui

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.multimodulepractice.main.impl.ui.profile.ProfileScreen
import com.example.multimodulepractice.main.impl.ui.profile.ProfileScreenEventHandler
import com.example.multimodulepractice.main.impl.ui.profile.ProfileViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    listViewModel: ListViewModel,
    profileViewModel: ProfileViewModel,
    navigateToLogin: () -> Unit
) {

    Box(modifier = Modifier.padding(bottom = 40.dp)) {
        NavHost(
            navController = navController,
            startDestination = SCREEN_MAP_ROUTE
        ) {
            composable(route = SCREEN_MAP_ROUTE) {
                Box(modifier = Modifier.fillMaxSize())
            }

            composable(
                route = SCREEN_LIST_ROUTE,
                enterTransition = {
                    if (initialState.destination.route == SCREEN_MAP_ROUTE) {
                        slideInHorizontally {
                            it
                        }
                    } else {
                        slideInHorizontally {
                            -it
                        }
                    }
                },
                exitTransition = {
                    if (targetState.destination.route == SCREEN_MAP_ROUTE) {
                        slideOutHorizontally {
                            it
                        }
                    } else {
                        slideOutHorizontally {
                            -it
                        }
                    }
                }
            ) {
                ListScreen(
                    listViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    listViewModel::onListAction
                )
            }

            composable(route = SCREEN_PROFILE_ROUTE,
                enterTransition = {
                    slideInHorizontally {
                        it
                    }
                },
                exitTransition = {
                    slideOutHorizontally {
                        it
                    }
                }) {
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