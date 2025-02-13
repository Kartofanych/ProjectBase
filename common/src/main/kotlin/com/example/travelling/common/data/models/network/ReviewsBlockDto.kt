package com.example.travelling.common.data.models.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ReviewsBlockDto(
    @SerializedName("avg_rate")
    val rating: Float,

    @SerializedName("total_count")
    val total: Int,

    @SerializedName("rate_list")
    val rateList: RateListObjectDto,

    @SerializedName("review_list")
    val reviewList: List<ReviewDto>,
)