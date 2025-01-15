package com.onboarding.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.navigation.injectedViewModel
import com.onboarding.api.OnboardingEntry
import com.onboarding.impl.di.DaggerOnboardingComponent
import com.onboarding.impl.di.OnboardingDependencies
import com.onboarding.impl.ui.OnboardingEventHandler
import com.onboarding.impl.ui.OnboardingScreen
import javax.inject.Inject

class OnboardingEntryImpl @Inject constructor(
    private val dependencies: OnboardingDependencies
) : OnboardingEntry() {

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
                    -it
                }
            },
            popExitTransition = {
                slideOutHorizontally {
                    -it
                }
            }
        ) {

            val viewModel = injectedViewModel {
                DaggerOnboardingComponent.factory().create(dependencies).viewModel
            }

            OnboardingEventHandler(
                uiEvent = viewModel.uiEvent,
                openLogin = { navController.navigate(LOGIN_ROUTE) },
            )

            OnboardingScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }

    private companion object {
        const val LOGIN_ROUTE = "login"
    }
}