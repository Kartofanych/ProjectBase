package com.example.multimodulepractice.main.impl.ui.list

import com.example.multimodulepractice.main.impl.data.local_models.list.CloseAttraction
import com.example.multimodulepractice.main.impl.data.local_models.list.VerticalAttraction

sealed interface ListUiState {

    object Error : ListUiState

    object Loading : ListUiState

    class Content(
        val popularList: List<VerticalAttraction>,
        val closeList: List<CloseAttraction>
    ) : ListUiState

}