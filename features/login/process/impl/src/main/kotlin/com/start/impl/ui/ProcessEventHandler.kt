package com.start.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ProcessEventHandler(
    uiEvent: Flow<ProcessEvent>,
    onBack: () -> Unit,
    openMain: () -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                ProcessEvent.BackEvent -> onBack()
                ProcessEvent.OpenMain -> openMain()
            }
        }
    }
}
