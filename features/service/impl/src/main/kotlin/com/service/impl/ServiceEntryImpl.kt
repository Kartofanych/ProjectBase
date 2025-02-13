package com.service.impl

import androidx.activity.compose.BackHandler
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.travelling.common.data.models.network.ObjectType
import com.example.travelling.common.navigation.injectedViewModel
import com.reviews.api.ReviewsEntry
import com.service.api.ServiceEntry
import com.service.impl.di.DaggerServiceComponent
import com.service.impl.di.ServiceDependencies
import com.service.impl.ui.ServiceAction
import com.service.impl.ui.ServiceScreen
import com.service.impl.ui.ServiceScreenEventHandler
import com.service.impl.ui.ServiceUiState
import javax.inject.Inject

class ServiceEntryImpl @Inject constructor(
    private val serviceDependencies: ServiceDependencies
) : ServiceEntry() {

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
                slideInVertically { it }
            },
            popEnterTransition = null,
            exitTransition = null,
            popExitTransition = {
                slideOutVertically { it }
            },
        ) {
            val serviceId = it.arguments?.getString(ARG_SERVICE_ID)!!
            val viewModel = injectedViewModel {
                DaggerServiceComponent.factory().create(serviceDependencies, serviceId).viewModel
            }

            ServiceScreenEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToReviews = {
                    mainNavController.navigate(
                        ReviewsEntry.destination(
                            serviceId,
                            ObjectType.SERVICE.name
                        )
                    )
                }
            )
            ServiceScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel.reviewModalStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )

            BackHandler {
                val state = viewModel.uiStateFlow.value
                if (state.state is ServiceUiState.DataState.Content && state.isScheduleVisible) {
                    viewModel.onAction(ServiceAction.ChangeScheduleVisibility)
                } else {
                    viewModel.onAction(ServiceAction.OnBackPressed)
                }
            }
        }
    }
}