package com.example.multimodulepractice.guide.impl.ui

import com.example.multimodulepractice.guide.impl.ui.models.Topic

sealed interface GuideUiState {

    object Error : GuideUiState

    object Loading : GuideUiState

    data class Content(
        val currentPage: Int,
        val topics: List<Topic>
    ) : GuideUiState

}