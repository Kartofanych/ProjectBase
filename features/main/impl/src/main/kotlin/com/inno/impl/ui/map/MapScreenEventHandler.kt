package com.inno.impl.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun MapScreenEventHandler(
    uiEvent: Flow<MapUiEvent>,
    navigateToGuide: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is MapUiEvent.OnGuideClicked -> navigateToGuide(event.landmarkId)
            }
        }
    }

}