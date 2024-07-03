package com.inno.impl

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.inno.api.GuideEntry
import com.inno.impl.ui.GuideScreen
import com.inno.impl.ui.GuideScreenEventHandler
import com.inno.impl.ui.GuideViewModel
import javax.inject.Inject

class GuideEntryImpl @Inject constructor() : GuideEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(featureRoute, arguments) {
            val landmarkId = it.arguments?.getString(ARG_LANDMARK_ID)!!
            val viewModel: GuideViewModel = hiltViewModel()

            viewModel.launch(landmarkId)

            GuideScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateBack = {
                    navController.popBackStack()
                },
            )
            GuideScreen(
                viewModel.uiStateFlow.collectAsState().value,
                viewModel::onGuideAction
            )
        }
    }

}