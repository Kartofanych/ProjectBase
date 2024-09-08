package com.service.impl

import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.service.api.ServiceEntry
import com.service.impl.di.DaggerServiceComponent
import com.service.impl.di.ServiceDependencies
import com.service.impl.ui.ServiceScreen
import com.service.impl.ui.ServiceScreenEventHandler
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
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    },
                )
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
            )
            ServiceScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onServiceAction
            )
        }
    }
}