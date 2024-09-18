package com.search.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchEventHandler(
    uiEvent: Flow<SearchEvent>,
    navigateToAttraction: (String) -> Unit,
    navigateToService: (String) -> Unit,
    navigateBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is SearchEvent.OnOpenAttraction -> navigateToAttraction(event.id)
                is SearchEvent.OnOpenService -> navigateToService(event.id)
                SearchEvent.OnBackPressed -> navigateBack()
            }
        }
    }
}