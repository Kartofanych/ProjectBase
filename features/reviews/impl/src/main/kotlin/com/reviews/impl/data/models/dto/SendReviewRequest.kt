package com.reviews.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class SendReviewRequest(

    @SerializedName("uid")
    val id: String,

    @SerializedName("rating")
    val stars: Int,

    @SerializedName("comment")
    val text: String,
)