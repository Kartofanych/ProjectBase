package com.search.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.search.api.SearchFeatureEntry
import com.search.impl.di.DaggerListComponent
import com.search.impl.di.ListDependencies
import com.search.impl.ui.ListEventHandler
import com.search.impl.ui.ListScreen
import com.search.impl.ui.ListViewModel
import javax.inject.Inject

class ListFeatureImpl @Inject constructor(
    dependencies: ListDependencies,
    private val listViewModel: ListViewModel
) : SearchFeatureEntry() {

    init {
        DaggerListComponent.factory().create(dependencies)
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

            ListEventHandler(
                uiEvent = listViewModel.uiEvent,
                navigateToSearch = { mainNavController.navigate(SCREEN_SEARCH_ROUTE) },
                navigateToAttraction = { id -> mainNavController.navigate("$SCREEN_ATTRACTION_ROUTE/$id") },
            )

            ListScreen(
                listViewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                listViewModel::onListAction
            )
        }
    }

    private companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_SEARCH_ROUTE = "search"
        const val SCREEN_ATTRACTION_ROUTE = "attraction"
    }
}