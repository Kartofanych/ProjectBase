package com.splash.impl

import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.auth.models.AuthInfo
import com.example.travelling.common.navigation.injectedViewModel
import com.splash.api.LaunchEntry
import com.splash.impl.di.DaggerLaunchComponent
import com.splash.impl.di.LaunchDependencies
import com.splash.impl.di.LaunchScope
import com.splash.impl.ui.LaunchScreen
import com.splash.impl.ui.LaunchScreenEventHandler
import javax.inject.Inject

@LaunchScope
class LaunchEntryImpl @Inject constructor(
    private val launchDependencies: LaunchDependencies
) : LaunchEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(route = featureRoute) {
            val component = DaggerLaunchComponent.factory().create(launchDependencies)
            val viewModel = injectedViewModel { component.viewModel }
            val userPreferences = launchDependencies.userPreferences
            val authInfo = launchDependencies.authInfoManager.authInfo()

            val startDestination = when {
                !userPreferences.isOnboardingShown() -> ONBOARDING_ROUTE
                authInfo is AuthInfo.Guest -> LOGIN_ROUTE
                else -> MAIN_ROUTE
            }

            LaunchScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                start = { navController.navigate(startDestination) }
            )

            LaunchScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onSplashAction
            )
        }
    }

    private companion object {
        const val LOGIN_ROUTE = "login"
        const val MAIN_ROUTE = "main"
        const val ONBOARDING_ROUTE = "onboarding"
    }
}
