package com.search.impl

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimodulepractice.common.navigation.find
import com.example.multimodulepractice.common.navigation.register
import com.search.api.SearchEntry
import com.search.impl.di.DaggerSearchComponent
import com.search.impl.di.SearchDependencies
import com.search_filters.api.data.SearchFiltersEntry
import com.search_filters.api.data.SearchScreenEntry
import javax.inject.Inject

class SearchEntryImpl @Inject constructor(
    private val searchDependencies: SearchDependencies
) : SearchEntry() {

    private val component by lazy {
        DaggerSearchComponent.factory().create(searchDependencies)
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
            enterTransition = {
                slideInHorizontally {
                    it
                }
            },
            popEnterTransition = null,
            exitTransition = null,
            popExitTransition = {
                slideOutHorizontally {
                    it
                }
            }
        ) {
            val searchNavController = rememberNavController()
            val searchScreenFeature = component.destinations.find<SearchScreenEntry>()
            val searchFiltersFeature = component.destinations.find<SearchFiltersEntry>()

            NavHost(
                navController = searchNavController,
                startDestination = "search_screen",
                modifier = Modifier
            ) {
                register(
                    searchScreenFeature,
                    navController = searchNavController,
                    modifier = Modifier,
                    mainNavController = mainNavController
                )

                register(
                    searchFiltersFeature,
                    navController = searchNavController,
                    modifier = Modifier,
                    mainNavController = mainNavController
                )
            }
        }
    }
}