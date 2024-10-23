package com.favourites.impl.data.models.dto

import com.google.gson.annotations.SerializedName

data class FavoriteAttractionDto(

    @SerializedName("uid")
    val id: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("rating")
    val rating: Float,
)
