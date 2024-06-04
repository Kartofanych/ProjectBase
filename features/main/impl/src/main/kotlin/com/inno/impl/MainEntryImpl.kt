package com.inno.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.common.Destinations
import com.inno.api.MainEntry
import javax.inject.Inject

class MainEntryImpl @Inject constructor() : MainEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        // view models, navigation here
        MainScreen()
    }

}