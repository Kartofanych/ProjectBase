package com.example.multimodulepractice.main.impl.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun MapScreenEventHandler(
    uiEvent: Flow<MapUiEvent>
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
        }
    }

}