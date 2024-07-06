package com.inno.impl.ui

import com.inno.impl.ui.models.Topic

sealed interface GuideUiState {

    object Error : GuideUiState

    object Loading : GuideUiState

    data class Content(
        val currentPage: Int,
        val topics: List<Topic>
    ) : GuideUiState

}