package com.splash.impl.ui

sealed interface SplashUiState {
    object Loading: SplashUiState

    object OldVersion: SplashUiState
}