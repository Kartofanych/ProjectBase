package com.splash.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.splash.impl.ui.composables.SplashScreenAppVersion

@Composable
fun SplashScreen(uiState: SplashUiState, onAction: (SplashAction) -> Unit) {
    when (uiState) {
        SplashUiState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())
        SplashUiState.OldVersion -> SplashScreenAppVersion(onAction)
    }
}