package com.favourites.impl.data.models.dto

import com.google.gson.annotations.SerializedName

class LikeRequestDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("is_liked")
    val isLiked: Boolean
)