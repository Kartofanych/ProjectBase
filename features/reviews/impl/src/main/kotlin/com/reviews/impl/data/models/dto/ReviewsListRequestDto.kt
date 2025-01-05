package com.reviews.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ReviewsListRequestDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("cursor")
    val cursor: String,

    @SerializedName("sort_id")
    val sortId: String?,
)
