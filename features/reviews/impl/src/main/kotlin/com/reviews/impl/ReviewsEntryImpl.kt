package com.reviews.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.data.models.network.ObjectType
import com.example.travelling.common.navigation.injectedViewModel
import com.reviews.api.ReviewsEntry
import com.reviews.impl.di.DaggerReviewsComponent
import com.reviews.impl.di.ReviewsComponent
import com.reviews.impl.di.ReviewsDependencies
import com.reviews.impl.ui.ReviewsEventHandler
import com.reviews.impl.ui.ReviewsScreen
import javax.inject.Inject

class ReviewsEntryImpl @Inject constructor(
    private val reviewsDependencies: ReviewsDependencies
) : ReviewsEntry() {

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
                slideInHorizontally {
                    it
                }
            },
            exitTransition = {
                slideOutHorizontally {
                    it
                }
            },
            popExitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {

            val id = it.arguments?.getString(ARG_OBJECT_ID)!!
            val type = it.arguments?.getString(ARG_OBJECT_TYPE)!!

            val viewModel = injectedViewModel {
                DaggerReviewsComponent.factory()
                    .create(
                        ReviewsComponent.ReviewsData(
                            id = id,
                            objectType = ObjectType.valueOf(type)
                        ),
                        reviewsDependencies
                    ).viewModel
            }

            ReviewsEventHandler(
                uiEvent = viewModel.uiEvent,
                onBack = { navController.popBackStack() }
            )

            ReviewsScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }
}
