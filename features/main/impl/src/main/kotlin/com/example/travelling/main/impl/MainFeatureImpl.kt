package com.example.travelling.main.impl

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelling.common.navigation.find
import com.example.travelling.common.navigation.injectedViewModel
import com.example.travelling.common.navigation.register
import com.example.travelling.main.MainFeatureEntry
import com.example.travelling.main.impl.di.DaggerMainComponent
import com.example.travelling.main.impl.di.MainDependencies
import com.example.travelling.main.impl.ui.MainScreen
import com.example.travelling.main.impl.ui.MainScreenEventHandler
import com.favourites.api.FavouritesFeatureEntry
import com.main_map.api.MapFeatureEntry
import com.search.api.SearchFeatureEntry
import javax.inject.Inject

class MainFeatureImpl @Inject constructor(
    private val mainDependencies: MainDependencies
) : MainFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            route = featureRoute,
            enterTransition = {
                when (initialState.destination.route) {
                    SCREEN_LOGIN_ROUTE -> slideInHorizontally { it }
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    SCREEN_LOGIN_ROUTE -> slideOutHorizontally { it / 3 }
                    else -> null
                }
            }
        ) {
            val component = DaggerMainComponent.factory().create(mainDependencies)

            val mapEntry = component.destinations.find<MapFeatureEntry>()
            val searchEntry = component.destinations.find<SearchFeatureEntry>()
            val favouritesEntry = component.destinations.find<FavouritesFeatureEntry>()

            val insideNavController = rememberNavController()
            val viewModel = injectedViewModel {
                component.viewModel
            }

            MainScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                openMap = { insideNavController.navigate(SCREEN_MAP_ROUTE) },
                openSearch = { insideNavController.navigate(SCREEN_SEARCH_ROUTE) },
                openFavorites = { insideNavController.navigate(SCREEN_FAVOURITES_ROUTE) },
            )

            Box(modifier = modifier.fillMaxSize()) {

                NavHost(
                    navController = insideNavController,
                    startDestination = SCREEN_MAP_ROUTE,
                    modifier = Modifier.fillMaxSize()
                ) {

                    register(
                        featureEntry = mapEntry,
                        navController = insideNavController,
                        mainNavController = navController,
                        modifier = modifier.padding(bottom = 60.dp)
                    )

                    register(
                        featureEntry = searchEntry,
                        navController = insideNavController,
                        mainNavController = navController,
                        modifier = modifier.padding(bottom = 60.dp)
                    )

                    register(
                        featureEntry = favouritesEntry,
                        navController = insideNavController,
                        mainNavController = navController,
                        modifier = modifier.padding(bottom = 60.dp)
                    )
                }

                MainScreen(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                    onAction = viewModel::onAction
                )
            }

            BackHandler(enabled = true, onBack = {})
        }
    }

    private companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_SEARCH_ROUTE = "main/search"
        const val SCREEN_FAVOURITES_ROUTE = "main/favourites"
        const val SCREEN_LOGIN_ROUTE = "login"
    }
}