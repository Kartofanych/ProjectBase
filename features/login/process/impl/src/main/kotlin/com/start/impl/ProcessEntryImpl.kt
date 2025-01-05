package com.start.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.navigation.injectedViewModel
import com.start.api.ProcessEntry
import com.start.impl.di.DaggerProcessComponent
import com.start.impl.di.ProcessDependencies
import com.start.impl.ui.ProcessEventHandler
import com.start.impl.ui.ProcessScreen
import javax.inject.Inject

class ProcessEntryImpl @Inject constructor(
    private val processDependencies: ProcessDependencies
) : ProcessEntry() {

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
                DaggerProcessComponent.factory().create(processDependencies).viewModel
            }

            ProcessEventHandler(
                uiEvent = viewModel.uiEvent,
                onBack = { navController.popBackStack() },
                openMain = { mainNavController.navigate(MAIN_ROUTE) }
            )

            ProcessScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }

    private companion object {
        const val MAIN_ROUTE = "main"
    }
}