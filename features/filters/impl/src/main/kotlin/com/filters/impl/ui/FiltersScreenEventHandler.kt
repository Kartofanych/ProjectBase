package com.filters.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun FiltersScreenEventHandler(uiEvent: Flow<FiltersUiEvent>, back: () -> Boolean) {

    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is FiltersUiEvent.OnClose -> back()
            }
        }
    }
}