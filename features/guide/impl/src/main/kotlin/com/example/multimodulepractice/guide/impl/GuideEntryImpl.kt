package com.example.multimodulepractice.guide.impl

import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.guide.GuideEntry
import com.example.multimodulepractice.guide.impl.ui.GuideViewModel
import com.example.multimodulepractice.guide.impl.ui.GuideScreen
import com.example.multimodulepractice.guide.impl.ui.GuideScreenEventHandler
import javax.inject.Inject

class GuideEntryImpl @Inject constructor(
    private val viewModel: GuideViewModel
) : GuideEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    },
                )
            },
        ) {
            val landmarkId = it.arguments?.getString(ARG_LANDMARK_ID)!!

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