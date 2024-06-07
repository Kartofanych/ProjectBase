package com.inno.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.common.Destinations
import com.inno.api.MainEntry
import com.inno.impl.ui.MainScreen
import javax.inject.Inject

class MainEntryImpl @Inject constructor() : MainEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        MainScreen()
    }

}