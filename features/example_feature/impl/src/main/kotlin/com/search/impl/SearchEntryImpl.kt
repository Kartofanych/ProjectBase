package com.search.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.search.api.SearchEntry
import com.search.impl.di.DaggerSearchComponent
import com.search.impl.di.SearchDependencies
import com.search.impl.ui.SearchEventHandler
import com.search.impl.ui.SearchScreen
import javax.inject.Inject

class SearchEntryImpl @Inject constructor(
    private val searchDependencies: SearchDependencies
) : SearchEntry() {

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
                //DaggerSearchComponent.factory().create(searchDependencies).viewModel
            }

            SearchEventHandler(
                uiEvent = viewModel.uiEvent
            )
            SearchScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }
}