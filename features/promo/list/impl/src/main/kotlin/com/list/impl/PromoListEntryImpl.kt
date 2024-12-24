package com.list.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.list.api.PromoListEntry
import com.list.impl.di.DaggerPromoListComponent
import com.list.impl.di.PromoListDependencies
import com.list.impl.ui.PromoListEventHandler
import com.list.impl.ui.PromoListScreen
import javax.inject.Inject

class PromoListEntryImpl @Inject constructor(
    private val dependencies: PromoListDependencies
) : PromoListEntry() {

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
                when {
                    initialState.destination.route?.contains("promo/item") == true -> {
                        slideInHorizontally { -it }
                    }

                    else -> slideInHorizontally {
                        it
                    }
                }
            },
            exitTransition = {
                when {
                    targetState.destination.route?.contains("promo/item") == true -> {
                        slideOutHorizontally { -it * 2 / 3 }
                    }

                    else -> slideOutHorizontally { it }
                }
            },
            popExitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {

            val viewModel = injectedViewModel {
                DaggerPromoListComponent.factory().create(dependencies).viewModel
            }

            PromoListEventHandler(
                uiEvent = viewModel.uiEvent,
                navigateToPromoItem = { id -> navController.navigate("promo/item/$id") },
                onClose = { mainNavController.popBackStack() }
            )

            PromoListScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }
}
