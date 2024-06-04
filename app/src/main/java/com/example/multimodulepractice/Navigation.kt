package com.example.multimodulepractice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.common.find
import com.example.multimodulepractice.di.LocalAppProvider
import com.inno.api.MainEntry

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val mainScreen = destinations.find<MainEntry>()

    Box(Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = mainScreen.destination(),
        ) {
            with(mainScreen) {
                composable(navController, destinations)
            }
            // ===== here add another feature entries =====
        }
    }
}
