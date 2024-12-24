package com.example.multimodulepractice.login.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.example.multimodulepractice.common.navigation.register
import com.example.multimodulepractice.login.LoginFeatureEntry
import com.example.multimodulepractice.login.impl.di.DaggerLoginComponent
import com.example.multimodulepractice.login.impl.di.LoginDependencies
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