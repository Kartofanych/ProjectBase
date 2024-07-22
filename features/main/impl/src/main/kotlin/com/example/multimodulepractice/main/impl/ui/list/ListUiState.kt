package com.example.multimodulepractice.main.impl.ui.list

import com.example.multimodulepractice.main.impl.data.local_models.list.Attraction

sealed interface ListUiState {

    object Error : ListUiState

    object Loading : ListUiState

    class Content(
        val popularList: List<Attraction>,
        val closeList: List<Attraction>
    ) : ListUiState

}