package com.example.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.api.AuthEntry
import com.example.common.Destinations
import javax.inject.Inject

class AuthEntryImpl @Inject constructor() : AuthEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        // view models, navigation here
        AuthScreen()
    }

}