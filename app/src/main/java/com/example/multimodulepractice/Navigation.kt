package com.example.multimodulepractice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.impl.AuthScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Box(Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "authScreen",
        ) {
            composable("authScreen") {
                AuthScreen()
            }
        }
    }
}
