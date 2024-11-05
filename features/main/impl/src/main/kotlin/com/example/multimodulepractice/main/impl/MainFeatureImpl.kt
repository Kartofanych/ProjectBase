package com.example.multimodulepractice.main.impl

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.example.multimodulepractice.common.navigation.register
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.main.impl.di.DaggerMainComponent
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.example.multimodulepractice.main.impl.ui.MainScreen
import com.example.multimodulepractice.main.impl.ui.MainScreenEventHandler
import com.favourites.api.FavouritesFeatureEntry
import com.main_map.api.MapFeatureEntry
import com.search.api.SearchFeatureEntry
import javax.inject.Inject

class MainFeatureImpl @Inject constructor(
    private val mainDependencies: MainDependencies
) : MainFeatureEntry() {

    private val component by lazy {
        DaggerMainComponent.factory().create(mainDependencies)
    }

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

            NavHost(
                navController = insideNavController,
                startDestination = SCREEN_MAP_ROUTE,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp)
            ) {

                register(
                    featureEntry = mapEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                    modifier = Modifier.padding(bottom = 60.dp)
                )

                register(
                    featureEntry = searchEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                    modifier = modifier
                )

                register(
                    featureEntry = favouritesEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                    modifier = modifier
                )
            }

            MainScreen(
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                onAction = viewModel::onAction
            )

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