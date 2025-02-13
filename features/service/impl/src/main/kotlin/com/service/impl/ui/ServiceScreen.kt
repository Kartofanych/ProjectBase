package com.service.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.travelling.common.composables.DefaultLoading
import com.service.impl.ui.ServiceUiState.DataState
import com.service.impl.ui.composables.ServiceContentV2
import com.service.impl.ui.composables.ServiceError

@Composable
fun ServiceScreen(
    uiState: ServiceUiState,
    reviewModalState: ReviewModalState,
    onAction: (ServiceAction) -> Unit
) {
    when (uiState.state) {
        is DataState.Content -> ServiceContentV2(
            uiState = uiState,
            reviewModalState = reviewModalState,
            onAction = onAction,
        )

        DataState.Error -> ServiceError(onAction)

        DataState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())
    }
}
