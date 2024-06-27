package com.inno.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inno.api.MainFeatureApi
import com.inno.impl.ui.BottomNavBar
import com.inno.impl.ui.BottomNavGraph
import javax.inject.Inject

private const val baseRoute = "main"

class MainFeatureImpl @Inject constructor() : MainFeatureApi {

    override val mainRoute = baseRoute

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(baseRoute) {
            val insideNavController = rememberNavController()
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                BottomNavGraph(
                    modifier = Modifier.padding(bottom = 40.dp),
                    navController = insideNavController
                )

                BottomNavBar(
                    modifier = Modifier
                        .height(60.dp)
                        .align(Alignment.BottomCenter),
                    navigateToMap = {
                        insideNavController.navigate(SCREEN_MAP_ROUTE)
                    },
                    navigateToList = {
                        insideNavController.navigate(SCREEN_LIST_ROUTE)
                    },
                    navigateToProfile = {
                        insideNavController.navigate(SCREEN_PROFILE_ROUTE)
                    }
                )
            }
        }

        //example of inside navigation
        /*composable(
            route = "$screenMapRoute/{$argumentKey}",
            arguments = listOf(
                navArgument(argumentKey) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val argument = Uri.decode(arguments.getString(argumentKey).orEmpty())

            ScreenB(
                argument = argument.orEmpty()
            )
        }
    }*/

    }

    companion object {
        const val SCREEN_MAP_ROUTE = "$baseRoute/map"
        const val SCREEN_LIST_ROUTE = "$baseRoute/list"
        const val SCREEN_PROFILE_ROUTE = "$baseRoute/profile"
    }
}