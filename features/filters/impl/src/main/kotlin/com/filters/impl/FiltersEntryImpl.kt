package com.filters.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.filters.api.FiltersEntry
import com.filters.impl.di.DaggerFiltersComponent
import com.filters.impl.di.FiltersDependencies
import com.filters.impl.di.FiltersScope
import com.filters.impl.ui.FiltersScreen
import com.filters.impl.ui.FiltersScreenEventHandler
import javax.inject.Inject

@FiltersScope
class FiltersEntryImpl @Inject constructor(
    private val filtersDependencies: FiltersDependencies
) : FiltersEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            popEnterTransition = {
                slideInHorizontally()
            },
            popExitTransition = {
                slideOutHorizontally()
            },
        ) {
            val viewModel = injectedViewModel {
                DaggerFiltersComponent.factory().create(filtersDependencies).viewModel
            }

            FiltersScreenEventHandler(
                uiEvent = viewModel.uiEvent
            )
            FiltersScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onFiltersAction
            )
        }
    }
}