package com.item.impl

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.multimodulepractice.common.navigation.injectedViewModel
import com.item.api.PromoItemEntry
import com.item.impl.di.DaggerPromoItemComponent
import com.item.impl.di.PromoItemDependencies
import com.item.impl.ui.PromoItemEventHandler
import com.item.impl.ui.PromoItemScreen
import javax.inject.Inject

class PromoItemEntryImpl @Inject constructor(
    private val dependencies: PromoItemDependencies
) : PromoItemEntry() {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        mainNavController: NavController,
        modifier: Modifier
    ) {

        navGraphBuilder.composable(
            featureRoute,
            arguments,
            enterTransition = { slideInHorizontally { it } },
            popEnterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popExitTransition = { slideOutHorizontally { it } }
        ) {
            val promoId = it.arguments?.getString(ARG_ITEM_ID)!!

            val viewModel = injectedViewModel {
                DaggerPromoItemComponent.factory().create(
                    promoId = promoId,
                    dependencies = dependencies
                ).viewModel
            }

            PromoItemEventHandler(
                uiEvent = viewModel.uiEvent,
                onBack = { navController.popBackStack() },
                navigateToAttraction = { id -> mainNavController.navigate(ATTRACTION_PREFIX + id) },
                navigateToService = { id -> mainNavController.navigate(SERVICE_PREFIX + id) },
            )

            PromoItemScreen(
                viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                viewModel::onAction
            )
        }
    }

    private companion object {
        const val ATTRACTION_PREFIX = "attraction/"
        const val SERVICE_PREFIX = "service/"
    }
}
