package com.filters.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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

    private val component by lazy {
        DaggerFiltersComponent.factory().create(filtersDependencies)
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
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
            },
        ) {
            val viewModel = injectedViewModel {
                component.viewModel
            }

            FiltersScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                back = { navController.popBackStack() }
            )
            FiltersScreen(
                viewModel.distanceStateFlow,
                viewModel.uiStateFlow.collectAsState().value,
                viewModel::onFiltersAction
            )
        }
    }
}