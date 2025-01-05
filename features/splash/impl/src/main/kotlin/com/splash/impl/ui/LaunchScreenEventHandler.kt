package com.splash.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchScreenEventHandler(uiEvent: Flow<LaunchEvent>, start: () -> Unit) {

    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                LaunchEvent.Start -> start()
            }
        }
    }
}