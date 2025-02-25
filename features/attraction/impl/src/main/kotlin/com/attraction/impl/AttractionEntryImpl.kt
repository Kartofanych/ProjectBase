package com.attraction.impl

import androidx.activity.compose.BackHandler
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.attraction.api.AttractionEntry
import com.attraction.impl.di.AttractionDependencies
import com.attraction.impl.di.DaggerAttractionComponent
import com.attraction.impl.ui.AttractionAction
import com.attraction.impl.ui.AttractionEventHandler
import com.attraction.impl.ui.AttractionScreen
import com.attraction.impl.ui.AttractionUiState
import com.example.travelling.common.data.models.network.ObjectType
import com.example.travelling.common.navigation.injectedViewModel
import com.reviews.api.ReviewsEntry
import javax.inject.Inject

class AttractionEntryImpl @Inject constructor(
    private val attractionDependencies: AttractionDependencies
) : AttractionEntry() {

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
            popEnterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popExitTransition = { slideOutVertically { it } }
        ) {
            val attractionId = it.arguments?.getString(ARG_ATTRACTION_ID)!!

            val viewModel = injectedViewModel {
                DaggerAttractionComponent.factory().create(
                    attractionDependencies,
                    attractionId
                ).viewModel
            }

            AttractionEventHandler(
                uiEvent = viewModel.uiEvent,
                openGuide = { id -> navController.navigate(GUIDE_PREFIX + id) },
                back = { navController.popBackStack() },
                navigateToAttraction = { id -> mainNavController.navigate(ATTRACTION_PREFIX + id) },
                navigateToService = { id -> mainNavController.navigate(SERVICE_PREFIX + id) },
                navigateToReviews = { id ->
                    mainNavController.navigate(
                        ReviewsEntry.destination(
                            id,
                            ObjectType.ATTRACTION.name
                        )
                    )
                }
            )

            AttractionScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel.reviewModalStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )

            BackHandler {
                val state = viewModel.uiStateFlow.value
                if (state is AttractionUiState.Content && state.landmark.schedule.isVisible) {
                    viewModel.onAction(AttractionAction.ChangeScheduleVisibility)
                } else {
                    viewModel.onAction(AttractionAction.OnBackPressed)
                }
            }
        }
    }

    private companion object {
        const val GUIDE_PREFIX = "guide/"
        const val ATTRACTION_PREFIX = "attraction/"
        const val SERVICE_PREFIX = "service/"
    }
}