package com.example.multimodulepractice.main.impl

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.example.multimodulepractice.main.MainFeatureEntry
import com.example.multimodulepractice.main.impl.di.DaggerMainComponent
import com.example.multimodulepractice.main.impl.di.MainDependencies
import com.example.multimodulepractice.main.impl.ui.BottomNavBar
import com.example.multimodulepractice.main.impl.ui.BottomNavGraph
import com.example.multimodulepractice.main.impl.ui.compose_elements.LandmarkBottomSheet
import javax.inject.Inject

class MainFeatureImpl @Inject constructor(
    private val mainDependencies: MainDependencies
) : MainFeatureEntry() {

    private val component by lazy {
        DaggerMainComponent.factory().create(mainDependencies)
    }

    private val attractionRepository by lazy {
        mainDependencies.attractionRepository
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            route = featureRoute,
            exitTransition = {
                slideOutHorizontally {
                    -it
                }
            },
            enterTransition = {
                EnterTransition.None
            }
        ) {
            val insideNavController = rememberNavController()
            val sheetState = rememberModalBottomSheetState()
            val landmark =
                attractionRepository.currentLandmark().collectAsStateWithLifecycle().value

            Box(modifier = Modifier.fillMaxSize()) {
                BottomNavGraph(
                    listViewModel = injectedViewModel {
                        component.listViewModel
                    },
                    profileViewModel = injectedViewModel {
                        component.profileViewModel
                    },
                    navController = insideNavController,
                    navigateToLogin = { navController.navigate("login") },
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

                if (landmark != null) {
                    LandmarkBottomSheet(
                        sheetState = sheetState,
                        onDismiss = attractionRepository::dismissLandmark,
                        recallLandmark = { attractionRepository.getLandmark(landmarkId = landmark.id) },
                        onOpenGuide = { navController.navigate("guide/${landmark.id}") },
                        onOpenService = { serviceId -> navController.navigate("service/$serviceId") },
                        landmark = landmark
                    )
                }
            }
        }
    }

    companion object {
        const val SCREEN_MAP_ROUTE = "main/map"
        const val SCREEN_LIST_ROUTE = "main/list"
        const val SCREEN_PROFILE_ROUTE = "main/profile"
    }
}