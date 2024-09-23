package com.search_filters.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.search_filters.api.data.SearchFiltersEntry
import com.search_filters.impl.di.DaggerSearchFiltersComponent
import com.search_filters.impl.di.SearchFiltersDependencies
import com.search_filters.impl.ui.SearchFiltersEventHandler
import com.search_filters.impl.ui.SearchFiltersScreen
import javax.inject.Inject

class SearchFiltersEntryImpl @Inject constructor(
    private val searchFiltersDependencies: SearchFiltersDependencies
) : SearchFiltersEntry() {

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
            exitTransition = {
                slideOutHorizontally {
                    it
                }
            },
            popExitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {

            val viewModel = injectedViewModel {
                DaggerSearchFiltersComponent.factory().create(searchFiltersDependencies).viewModel
            }

            SearchFiltersEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateBack = { navController.popBackStack() }
            )
            SearchFiltersScreen(
                viewModel.distanceStateFlow,
                viewModel.starsStateFlow,
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }
}