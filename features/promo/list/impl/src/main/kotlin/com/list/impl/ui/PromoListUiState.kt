package com.list.impl.ui

import com.list.impl.data.models.local.PromoCode

sealed interface PromoListUiState {

    object Error : PromoListUiState

    object Loading : PromoListUiState

    data class Content(val list: List<PromoCode>) : PromoListUiState
}
