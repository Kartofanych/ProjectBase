package com.start.impl

import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.navigation.injectedViewModel
import com.start.api.StartEntry
import com.start.impl.di.DaggerStartComponent
import com.start.impl.di.StartDependencies
import com.start.impl.ui.StartEventHandler
import com.start.impl.ui.StartScreen
import javax.inject.Inject

class StartEntryImpl @Inject constructor(
    private val startDependencies: StartDependencies
) : StartEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            enterTransition = null,
            exitTransition = {
                slideOutHorizontally { it }
            },
            popExitTransition = {
                slideOutHorizontally { it }
            }
        ) {

            val viewModel = injectedViewModel {
                DaggerStartComponent.factory().create(startDependencies).viewModel
            }

            StartEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateToMain = { mainNavController.navigate(MAIN_ENTRY) },
                navigateToLoginProcess = { navController.navigate(LOGIN_PROCESS_ENTRY) }
            )

            StartScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }

    private companion object {
        const val MAIN_ENTRY = "main"
        const val LOGIN_PROCESS_ENTRY = "login/process"
    }
}
