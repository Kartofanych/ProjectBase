package com.reviews.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ReviewsPageRequestDto(
    @SerializedName("uid")
    val id: String,
)
