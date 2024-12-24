package com.list.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun PromoListEventHandler(
    uiEvent: Flow<PromoListEvent>,
    navigateToPromoItem: (String) -> Unit,
    onClose: () -> Unit,
) {

    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is PromoListEvent.OnOpenPromo -> navigateToPromoItem(event.id)
                PromoListEvent.OnClose -> onClose()
            }
        }
    }
}
