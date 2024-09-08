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
import com.favourites.impl.ui.FavouritesViewModel
import com.favourites.impl.ui.ProfileScreen
import com.favourites.impl.ui.ProfileScreenEventHandler
import javax.inject.Inject

class FavouritesFeatureImpl @Inject constructor(
    dependencies: FavouritesDependencies,
    private val favouritesViewModel: FavouritesViewModel
) : FavouritesFeatureEntry() {

    init {
        DaggerFavouritesComponent.factory().create(dependencies)
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            route = SCREEN_FAVOURITES_ROUTE,
            enterTransition = {
                slideInHorizontally {
                    it
                }
            },
            exitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {
            ProfileScreenEventHandler(
                navigateToLogin = { mainNavController.navigate(SCREEN_LOGIN_ROUTE) },
                uiEvent = favouritesViewModel.uiEvent
            )

            ProfileScreen(
                favouritesViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                favouritesViewModel::onProfileAction
            )
        }
    }

    private companion object {
        const val SCREEN_FAVOURITES_ROUTE = "main/favourites"
        const val SCREEN_LOGIN_ROUTE = "login"
    }
}