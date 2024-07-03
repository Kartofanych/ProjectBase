package com.inno.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.inno.api.LoginFeatureEntry
import com.inno.api.MainFeatureEntry
import com.inno.impl.ui.LoginScreen
import javax.inject.Inject

class LoginFeatureImpl @Inject constructor(
    private val mainFeatureApi: MainFeatureEntry
) : LoginFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            LoginScreen(
                navigateToMain = {
                    navController.navigate(mainFeatureApi.featureRoute)
                }
            )
        }
    }

}