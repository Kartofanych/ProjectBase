package com.search_filters.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchFiltersEventHandler(
    uiEvent: Flow<SearchFiltersEvent>,
    navigateBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                SearchFiltersEvent.OnClose -> navigateBack()
            }
        }
    }
}