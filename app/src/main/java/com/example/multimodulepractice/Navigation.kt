package com.example.multimodulepractice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.api.AuthEntry
import com.example.common.find
import com.example.multimodulepractice.di.LocalAppProvider

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val authScreen = destinations.find<AuthEntry>()

    Box(Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = authScreen.destination(),
        ) {
            with(authScreen) {
                composable(navController, destinations)
            }
            // ===== here add another feature entries =====
        }
    }
}
