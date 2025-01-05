package com.example.travelling.login.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelling.common.navigation.find
import com.example.travelling.common.navigation.injectedViewModel
import com.example.travelling.common.navigation.register
import com.example.travelling.login.LoginFeatureEntry
import com.example.travelling.login.impl.di.DaggerLoginComponent
import com.example.travelling.login.impl.di.LoginDependencies
import com.start.api.ProcessEntry
import com.start.api.StartEntry
import javax.inject.Inject

class LoginFeatureImpl @Inject constructor(
    private val loginDependencies: LoginDependencies,
) : LoginFeatureEntry() {

    private val component by lazy {
        DaggerLoginComponent.factory().create(loginDependencies)
    }

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            val insideNavController = rememberNavController()
            val viewModel = injectedViewModel { component.viewModel }

            val startEntry = component.destinations.find<StartEntry>()
            val processEntry = component.destinations.find<ProcessEntry>()

            NavHost(
                navController = insideNavController,
                startDestination = "login/start",
                modifier = Modifier.fillMaxSize()
            ) {

                register(
                    featureEntry = startEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                )

                register(
                    featureEntry = processEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                )
            }
        }
    }
}