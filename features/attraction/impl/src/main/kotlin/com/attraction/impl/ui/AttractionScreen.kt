package com.attraction.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.attraction.impl.ui.composables.LandmarkContent
import com.example.multimodulepractice.common.composables.DefaultError
import com.example.multimodulepractice.common.composables.DefaultLoading

@Composable
fun AttractionScreen(uiState: AttractionUiState, onAction: (AttractionAction) -> Unit) {

    when (uiState) {
        is AttractionUiState.Content -> {
            LandmarkContent(
                landmark = uiState.landmark,
                onAction = onAction
            )
        }

        is AttractionUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(AttractionAction.RecallAttraction) }
        )

        AttractionUiState.Loading -> DefaultLoading(
            modifier = Modifier.fillMaxSize()
        )
    }
}