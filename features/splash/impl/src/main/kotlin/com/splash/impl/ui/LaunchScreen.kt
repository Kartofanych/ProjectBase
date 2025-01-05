package com.splash.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.travelling.common.composables.DefaultLoading
import com.splash.impl.ui.composables.LaunchScreenAppVersion

@Composable
fun LaunchScreen(uiState: LaunchUiState, onAction: (LaunchAction) -> Unit) {
    when (uiState) {
        LaunchUiState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())
        LaunchUiState.OldVersion -> LaunchScreenAppVersion(onAction)
    }
}