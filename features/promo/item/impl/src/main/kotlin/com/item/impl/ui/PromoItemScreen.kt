package com.item.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultError
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.item.impl.ui.composables.PromoItemContent

@Composable
fun PromoItemScreen(uiState: PromoItemUiState, onAction: (PromoItemAction) -> Unit) {
    when (uiState) {
        is PromoItemUiState.Content -> PromoItemContent(uiState.item, onAction)

        PromoItemUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(PromoItemUiState.OnReload) }
        )

        PromoItemUiState.Loading -> DefaultLoading(Modifier.fillMaxSize())
    }
}
