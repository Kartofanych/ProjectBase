package com.inno.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.inno.api.LoginFeatureApi
import com.inno.impl.ui.LoginScreen
import javax.inject.Inject

private const val baseRoute = "login"
private const val mainRoute = "main"

class LoginFeatureImpl @Inject constructor() : LoginFeatureApi {

    override val loginRoute: String = baseRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(baseRoute) {
            LoginScreen(
                navigateToMain = {
                    navController.navigate(mainRoute)
                }
            )
        }
    }

}