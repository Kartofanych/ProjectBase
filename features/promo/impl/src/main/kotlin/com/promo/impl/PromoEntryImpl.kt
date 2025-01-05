package com.promo.impl

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelling.common.navigation.find
import com.example.travelling.common.navigation.injectedViewModel
import com.example.travelling.common.navigation.register
import com.item.api.PromoItemEntry
import com.list.api.PromoListEntry
import com.promo.api.PromoEntry
import com.promo.impl.di.DaggerPromoComponent
import com.promo.impl.di.PromoDependencies
import com.promo.impl.ui.PromoEventHandler
import javax.inject.Inject

class PromoEntryImpl @Inject constructor(
    private val promoDependencies: PromoDependencies
) : PromoEntry() {

    private val component by lazy {
        DaggerPromoComponent.factory().create(promoDependencies)
    }

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
            val insideNavController = rememberNavController()

            val viewModel = injectedViewModel { component.viewModel }

            val listEntry = component.destinations.find<PromoListEntry>()
            val itemEntry = component.destinations.find<PromoItemEntry>()

            NavHost(
                navController = insideNavController,
                startDestination = "promo/list",
                modifier = Modifier.fillMaxSize()
            ) {

                register(
                    featureEntry = listEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                )

                register(
                    featureEntry = itemEntry,
                    navController = insideNavController,
                    mainNavController = navController,
                )
            }

            PromoEventHandler(uiEvent = viewModel.uiEvent)
        }
    }
}