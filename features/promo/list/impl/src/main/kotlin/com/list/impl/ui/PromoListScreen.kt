package com.list.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultError
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.list.impl.ui.composables.PromoListContent

@Composable
fun PromoListScreen(uiState: PromoListUiState, onAction: (PromoListAction) -> Unit) {

    when (uiState) {
        is PromoListUiState.Content -> PromoListContent(uiState, onAction)

        PromoListUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(PromoListAction.OnReload) }
        )

        PromoListUiState.Loading -> DefaultLoading(Modifier.fillMaxSize())
    }
}
