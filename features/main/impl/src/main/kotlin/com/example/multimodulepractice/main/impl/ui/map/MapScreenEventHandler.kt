package com.example.multimodulepractice.main.impl.ui.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun MapScreenEventHandler(
    uiEvent: Flow<MapUiEvent>,
    openFilters: () -> Unit,
    openAttraction: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                MapUiEvent.OnFiltersOpen -> openFilters()
                is MapUiEvent.OnAttractionOpen -> openAttraction(event.id)
            }
        }
    }
}