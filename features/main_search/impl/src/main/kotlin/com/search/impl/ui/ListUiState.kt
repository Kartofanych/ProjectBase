package com.search.impl.ui

import com.main.common.data.local.Attraction

sealed interface ListUiState {

    object Error : ListUiState

    object Loading : ListUiState

    class Content(
        val popularList: List<Attraction>,
        val closeList: List<Attraction>
    ) : ListUiState

}