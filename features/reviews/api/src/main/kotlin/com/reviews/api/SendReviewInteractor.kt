package com.reviews.api

import com.example.travelling.common.data.models.local.ResponseState

interface SendReviewInteractor {
    suspend fun sendReview(
        id: String,
        text: String,
        stars: Int,
    ): ResponseState<Unit>
}