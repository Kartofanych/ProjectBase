package com.example.multimodulepractice.main.impl

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.navigation.register
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.main.impl.di.DaggerMainComponent
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.example.multimodulepractice.main.impl.ui.BottomNavBar
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

        val mapEntry = component.destinations.find<MapFeatureEntry>()
        val searchEntry = component.destinations.find<SearchFeatureEntry>()
        val favouritesEntry = component.destinations.find<FavouritesFeatureEntry>()

        navGraphBuilder.composable(
            route = featureRoute,
            exitTransition = {
                slideOutHorizontally {
                    -it
                }
            },
            enterTransition = {
                EnterTransition.None
            }
        ) {
            val insideNavController = rememberNavController()

            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.padding(bottom = 60.dp)) {
                    NavHost(
                        navController = insideNavController,
                        startDestination = SCREEN_MAP_ROUTE
                    ) {

                        register(
                            featureEntry = mapEntry,
                            navController = insideNavController,
                            mainNavController = navController,
                            modifier = Modifier
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
                }

                BottomNavBar(
                    modifier = Modifier
                        .height(60.dp)
                        .align(Alignment.BottomCenter),
                    navigateToMap = {
                        insideNavController.navigate(SCREEN_MAP_ROUTE)
                    },
                    navigateToList = {
                        insideNavController.navigate(SCREEN_SEARCH_ROUTE)
                    },
                    navigateToProfile = {
                        insideNavController.navigate(SCREEN_FAVOURITES_ROUTE)
                    }
                )
            }
        }
    }

    private companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_SEARCH_ROUTE = "main/search"
        const val SCREEN_FAVOURITES_ROUTE = "main/favourites"
    }
}