package com.search.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.navigation.injectedViewModel
import com.search.api.SearchFeatureEntry
import com.search.impl.di.DaggerListComponent
import com.search.impl.di.ListDependencies
import com.search.impl.ui.ListEventHandler
import com.search.impl.ui.ListScreen
import javax.inject.Inject

class ListFeatureImpl @Inject constructor(
    private val dependencies: ListDependencies,
) : SearchFeatureEntry() {

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
                    SCREEN_MAP_ROUTE -> slideInHorizontally { it }
                    SCREEN_FAVOURITES_ROUTE -> slideInHorizontally { -it }
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    SCREEN_MAP_ROUTE -> slideOutHorizontally { it }
                    SCREEN_FAVOURITES_ROUTE -> slideOutHorizontally { -it / 3 }
                    else -> null
                }
            },
        ) {

            DaggerListComponent.factory().create(dependencies)

            val viewModel = injectedViewModel {
                dependencies.listViewModel
            }

            ListEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateToSearch = { mainNavController.navigate(SCREEN_SEARCH_ROUTE) },
                navigateToAttraction = { id -> mainNavController.navigate("$SCREEN_ATTRACTION_ROUTE/$id") },
                navigateToService = { id -> mainNavController.navigate("$SCREEN_SERVICE_ROUTE/$id") },
            )

            ListScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onListAction
            )
        }
    }

    private companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_FAVOURITES_ROUTE = "main/favourites"
        const val SCREEN_SEARCH_ROUTE = "search"
        const val SCREEN_ATTRACTION_ROUTE = "attraction"
        const val SCREEN_SERVICE_ROUTE = "service"
    }
}