package com.service.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.service.impl.ui.ServiceUiState.DataState
import com.service.impl.ui.composables.ServiceContent
import com.service.impl.ui.composables.ServiceError

@Composable
fun ServiceScreen(uiState: ServiceUiState, onAction: (ServiceAction) -> Unit) {
    when (uiState.state) {
        is DataState.Content -> ServiceContent(service = uiState.state.service, onAction = onAction)

        DataState.Error -> ServiceError(onAction)

        DataState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())
    }
}
