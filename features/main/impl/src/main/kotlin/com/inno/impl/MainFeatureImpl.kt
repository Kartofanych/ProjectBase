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
import com.inno.api.GuideEntry
import com.inno.api.MainFeatureEntry
import com.inno.impl.ui.BottomNavBar
import com.inno.impl.ui.BottomNavGraph
import javax.inject.Inject

class MainFeatureImpl @Inject constructor(
    private val guideEntry: GuideEntry
) : MainFeatureEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            val insideNavController = rememberNavController()
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                BottomNavGraph(
                    modifier = Modifier.padding(bottom = 40.dp),
                    navController = insideNavController,
                    navigateToGuide = {
                        navController.navigate(guideEntry.destination(it))
                    }
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
    }

    companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_LIST_ROUTE = "main/list"
        const val SCREEN_PROFILE_ROUTE = "main/profile"
    }
}