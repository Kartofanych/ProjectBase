package com.inno.impl.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun MapEventHandler(
    uiEvent: Flow<MapUiEvent>
) {
    LaunchedEffect(Unit) {
        uiEvent.collect {
            //some navigation events here
        }
    }

}