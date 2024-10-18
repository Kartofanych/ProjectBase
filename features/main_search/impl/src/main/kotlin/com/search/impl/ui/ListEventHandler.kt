package com.search.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ListEventHandler(
    uiEvent: Flow<ListEvent>,
    navigateToSearch: () -> Unit,
    navigateToAttraction: (String) -> Unit,
    navigateToService: (String) -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                ListEvent.OpenSearch -> navigateToSearch()
                is ListEvent.OpenAttraction -> navigateToAttraction(event.id)
                is ListEvent.OpenService -> navigateToService(event.id)
            }
        }
    }
}