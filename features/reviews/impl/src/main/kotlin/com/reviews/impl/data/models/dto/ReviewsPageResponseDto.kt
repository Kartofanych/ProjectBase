package com.reviews.impl.data.models.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.RateListObjectDto
import com.example.travelling.common.data.models.network.ReviewDto
import com.google.gson.annotations.SerializedName

@Keep
class ReviewsPageResponseDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("cursor")
    val cursor: String? = null,

    @SerializedName("reviews")
    val reviewsBlockDto: ReviewsBlockDto
)

@Keep
class ReviewsBlockDto(
    @SerializedName("review_list")
    val items: List<ReviewDto>,

    @SerializedName("rate_list")
    val ratings: RateListObjectDto,

    @SerializedName("total_count")
    val total: Int,

    @SerializedName("avg_rate")
    val rating: Float,
)
