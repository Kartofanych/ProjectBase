package com.example.multimodulepractice.login.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.login.LoginFeatureEntry
import com.example.multimodulepractice.login.impl.ui.LoginScreen
import com.example.multimodulepractice.login.impl.ui.LoginViewModel
import javax.inject.Inject

class LoginFeatureImpl @Inject constructor(
    private val viewModel: LoginViewModel
) : LoginFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            LoginScreen(
                viewModel,
                navigateToMain = {
                    navController.navigate("main")
                }
            )
        }
    }
}