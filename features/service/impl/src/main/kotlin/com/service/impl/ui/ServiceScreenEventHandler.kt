package com.service.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ServiceScreenEventHandler(
    uiEvent: Flow<ServiceUiEvent>,
    navigateBack: () -> Boolean,
    navigateToReviews: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                ServiceUiEvent.OnBackPressed -> navigateBack()
                is ServiceUiEvent.OpenReviews -> navigateToReviews(event.id)
            }
        }
    }
}