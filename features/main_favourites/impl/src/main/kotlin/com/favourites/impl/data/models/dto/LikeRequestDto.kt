package com.favourites.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LikeRequestDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("is_liked")
    val isLiked: Boolean
)