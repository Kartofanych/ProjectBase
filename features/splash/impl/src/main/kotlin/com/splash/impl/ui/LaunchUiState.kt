package com.splash.impl.ui

sealed interface LaunchUiState {

    object Loading : LaunchUiState

    object OldVersion : LaunchUiState

    object Error : LaunchUiState
}