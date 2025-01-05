package com.attraction.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.attraction.impl.ui.composables.AttractionContent
import com.example.travelling.common.composables.DefaultError
import com.example.travelling.common.composables.DefaultLoading

@Composable
fun AttractionScreen(
    uiState: AttractionUiState,
    reviewModalState: ReviewModalState,
    onAction: (AttractionAction) -> Unit
) {

    when (uiState) {
        is AttractionUiState.Content -> AttractionContent(
            uiState = uiState,
            reviewModalState = reviewModalState,
            onAction = onAction
        )

        is AttractionUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(AttractionAction.RecallAttraction) }
        )

        AttractionUiState.Loading -> DefaultLoading(
            modifier = Modifier.fillMaxSize()
        )
    }
}