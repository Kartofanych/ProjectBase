package com.inno.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inno.api.GuideEntry
import com.inno.api.MainFeatureEntry
import com.inno.impl.repositories.LandmarkRepository
import com.inno.impl.ui.BottomNavBar
import com.inno.impl.ui.BottomNavGraph
import com.inno.impl.ui.LandmarkBottomSheet
import javax.inject.Inject

class MainFeatureImpl @Inject constructor(
    private val guideEntry: GuideEntry,
    private val landmarkRepository: LandmarkRepository
) : MainFeatureEntry() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(featureRoute) {
            val insideNavController = rememberNavController()
            val sheetState = rememberModalBottomSheetState()
            val landmark = landmarkRepository.currentLandmark.collectAsState().value

            Box(modifier = Modifier.fillMaxSize()) {
                BottomNavGraph(navController = insideNavController)

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
                        onDismiss = landmarkRepository::dismissLandmark,
                        recallLandmark = { landmarkRepository.getLandmark(landmarkId = landmark.id) },
                        onOpenGuide = { navController.navigate(guideEntry.destination(landmark.id)) },
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