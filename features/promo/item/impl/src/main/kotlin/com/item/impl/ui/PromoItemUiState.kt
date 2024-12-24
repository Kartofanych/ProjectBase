package com.item.impl.ui

import com.item.impl.data.models.local.PromoItem

sealed interface PromoItemUiState {

    object Error : PromoItemUiState

    object Loading : PromoItemUiState

    object OnReload : PromoItemAction

    data class Content(val item: PromoItem) : PromoItemUiState
}
