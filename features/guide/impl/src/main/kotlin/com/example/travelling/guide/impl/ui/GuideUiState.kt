package com.example.travelling.guide.impl.ui

import com.example.travelling.guide.impl.ui.models.Topic

sealed interface GuideUiState {

    object Error : GuideUiState

    object Loading : GuideUiState

    data class Content(
        val currentPage: Int,
        val topics: List<Topic>
    ) : GuideUiState

}