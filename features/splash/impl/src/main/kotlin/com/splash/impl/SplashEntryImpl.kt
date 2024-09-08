package com.splash.impl

import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.auth.models.AuthInfo
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.splash.api.SplashEntry
import com.splash.impl.di.DaggerSplashComponent
import com.splash.impl.di.SplashDependencies
import com.splash.impl.di.SplashScope
import com.splash.impl.ui.SplashScreen
import com.splash.impl.ui.SplashScreenEventHandler
import javax.inject.Inject

@SplashScope
class SplashEntryImpl @Inject constructor(
    private val splashDependencies: SplashDependencies
) : SplashEntry() {

    private val component by lazy {
        DaggerSplashComponent.factory().create(splashDependencies)
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {
        val startDestination = when (splashDependencies.authInfoManager.authInfo()) {
            AuthInfo.Guest -> "login"
            is AuthInfo.User -> "main"
        }

        navGraphBuilder.composable(
            route = featureRoute
        ) {
            val viewModel = injectedViewModel {
                component.viewModel
            }

            SplashScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                start = { navController.navigate(startDestination) }
            )

            SplashScreen(viewModel.uiStateFlow.collectAsStateWithLifecycle().value, viewModel::onSplashAction)
        }
    }
}
