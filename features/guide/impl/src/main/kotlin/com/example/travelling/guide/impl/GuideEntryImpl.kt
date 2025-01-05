package com.example.travelling.guide.impl

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.navigation.injectedViewModel
import com.example.travelling.guide.GuideEntry
import com.example.travelling.guide.impl.di.DaggerGuideComponent
import com.example.travelling.guide.impl.di.GuideDependencies
import com.example.travelling.guide.impl.ui.GuideScreen
import com.example.travelling.guide.impl.ui.GuideScreenEventHandler
import javax.inject.Inject

class GuideEntryImpl @Inject constructor(
    private val guideDependencies: GuideDependencies
) : GuideEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            enterTransition = {
                slideInVertically {
                    it
                }
            },
            popEnterTransition = null,
            exitTransition = null,
            popExitTransition = {
                slideOutVertically {
                    it
                }
            },
        ) {
            val landmarkId = it.arguments?.getString(ARG_LANDMARK_ID)!!
            val viewModel = injectedViewModel {
                DaggerGuideComponent.factory().create(guideDependencies, landmarkId).viewModel
            }

            GuideScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateBack = {
                    navController.popBackStack()
                },
            )
            GuideScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onGuideAction
            )
        }
    }
}