package com.reviews.impl.data.models.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.ReviewDto
import com.google.gson.annotations.SerializedName

@Keep
class ReviewsListResponseDto(
    @SerializedName("reviews")
    val items: List<ReviewDto>,

    @SerializedName("cursor")
    val cursor: String,
)
