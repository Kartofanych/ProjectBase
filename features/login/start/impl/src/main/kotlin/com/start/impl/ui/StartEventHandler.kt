package com.start.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun StartEventHandler(
    uiEvent: Flow<StartEvent>,
    navigateToMain: () -> Unit,
    navigateToLoginProcess: () -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                StartEvent.OnGuestLogin -> navigateToMain()
                StartEvent.OnLogin -> navigateToLoginProcess()
            }
        }
    }
}
