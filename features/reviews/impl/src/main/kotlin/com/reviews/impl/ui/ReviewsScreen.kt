package com.reviews.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.travelling.common.composables.DefaultError
import com.example.travelling.common.composables.DefaultLoading
import com.reviews.impl.ui.composables.ReviewsContent

@Composable
fun ReviewsScreen(uiState: ReviewsUiState, onAction: (ReviewsAction) -> Unit) {

    when (uiState) {
        is ReviewsUiState.Content -> ReviewsContent(uiState, onAction)

        ReviewsUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(ReviewsAction.OnReload) }
        )

        ReviewsUiState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())
    }
}
