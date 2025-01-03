package com.search.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.multimodulepractice.common.composables.DefaultError
import com.example.multimodulepractice.common.composables.DefaultLoading
import com.search.impl.ui.composables.ListContent

@Composable
fun ListScreen(uiState: ListUiState, onAction: (ListAction) -> Unit) {
    when (uiState) {
        is ListUiState.Content -> ListContent(uiState, onAction)

        ListUiState.Loading -> DefaultLoading(modifier = Modifier.fillMaxSize())

        ListUiState.Error -> DefaultError(
            modifier = Modifier.fillMaxSize(),
            onReload = { onAction(ListAction.ReloadList) }
        )
    }
}
