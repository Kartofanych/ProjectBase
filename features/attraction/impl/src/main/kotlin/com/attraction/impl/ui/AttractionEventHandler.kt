package com.attraction.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun AttractionEventHandler(
    uiEvent: Flow<AttractionEvent>,
    openGuide: (String) -> Unit,
    openService: (String) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is AttractionEvent.OpenGuide -> openGuide(event.attractionId)
                is AttractionEvent.OpenService -> openService(event.serviceId)
            }
        }
    }
}