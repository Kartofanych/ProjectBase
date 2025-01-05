package com.reviews.impl.ui

import com.example.travelling.common.data.models.local.RatingBlock
import com.example.travelling.common.data.models.local.Review

sealed interface ReviewsUiState {
    object Loading : ReviewsUiState

    object Error : ReviewsUiState

    data class Content(
        val title: String,
        val ratingBlock: RatingBlock,
        val ratings: List<Int>,
        val reviews: List<Review>,
        val loading: Boolean = false,
        val error: Boolean = false,
    ) : ReviewsUiState
}
