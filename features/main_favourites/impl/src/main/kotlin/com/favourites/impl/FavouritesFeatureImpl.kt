package com.favourites.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.favourites.api.FavouritesFeatureEntry
import com.favourites.impl.di.DaggerFavouritesComponent
import com.favourites.impl.di.FavouritesDependencies
import com.favourites.impl.ui.ProfileScreen
import com.favourites.impl.ui.ProfileScreenEventHandler
import javax.inject.Inject

class FavouritesFeatureImpl @Inject constructor(
    private val dependencies: FavouritesDependencies
) : FavouritesFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {
        DaggerFavouritesComponent.factory().create(dependencies)

        navGraphBuilder.composable(
            route = SCREEN_FAVOURITES_ROUTE,
            enterTransition = {
                when (initialState.destination.route) {
                    SCREEN_LIST_ROUTE -> slideInHorizontally { it }
                    SCREEN_MAP_ROUTE -> slideInHorizontally { it }
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    SCREEN_LIST_ROUTE -> slideOutHorizontally { it / 3 }
                    SCREEN_MAP_ROUTE -> slideOutHorizontally { it }
                    else -> null
                }
            }
        ) {
            dependencies.favouritesViewModel.onAttach()
            ProfileScreenEventHandler(
                navigateToLogin = { mainNavController.navigate(SCREEN_LOGIN_ROUTE) },
                navigateToAttraction = { id -> mainNavController.navigate("$SCREEN_ATTRACTION_ROUTE/$id") },
                uiEvent = dependencies.favouritesViewModel.uiEvent
            )

            ProfileScreen(
                dependencies.favouritesViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                dependencies.favouritesViewModel::onAction
            )
        }
    }

    private companion object {
        const val SCREEN_LIST_ROUTE = "main/search"
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_FAVOURITES_ROUTE = "main/favourites"
        const val SCREEN_LOGIN_ROUTE = "login"
        const val SCREEN_ATTRACTION_ROUTE = "attraction"
    }
}