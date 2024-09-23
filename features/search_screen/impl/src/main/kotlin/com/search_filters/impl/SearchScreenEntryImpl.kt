package com.search_filters.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.search_filters.api.data.SearchScreenEntry
import com.search_filters.impl.di.DaggerSearchScreenComponent
import com.search_filters.impl.di.SearchScreenDependencies
import com.search_filters.impl.ui.SearchEventHandler
import com.search_filters.impl.ui.SearchScreen
import javax.inject.Inject

class SearchScreenEntryImpl @Inject constructor(
    private val searchDependencies: SearchScreenDependencies
) : SearchScreenEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            enterTransition = {
                slideInHorizontally {
                    it
                }
            },
            popEnterTransition = null,
            exitTransition = null,
            popExitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {

            val viewModel = injectedViewModel {
                DaggerSearchScreenComponent.factory().create(searchDependencies).viewModel
            }

            SearchEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateToAttraction = { id -> mainNavController.navigate("$SCREEN_ATTRACTION_ROUTE/$id") },
                navigateToService = { id -> mainNavController.navigate("$SCREEN_SERVICE_ROUTE/$id") },
                navigateToFilters = { navController.navigate(SCREEN_SEARCH_FILTERS_ROUTE) },
                navigateBack = { mainNavController.popBackStack() }
            )

            SearchScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }

    private companion object {
        const val SCREEN_SERVICE_ROUTE = "service"
        const val SCREEN_ATTRACTION_ROUTE = "attraction"
        const val SCREEN_SEARCH_FILTERS_ROUTE = "search_filters"
    }
}