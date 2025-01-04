package com.reviews.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ReviewsEventHandler(
    uiEvent: Flow<ReviewsEvent>,
    onBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                ReviewsEvent.OnBack -> onBack()
            }
        }
    }
}
