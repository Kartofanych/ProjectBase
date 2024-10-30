package com.search.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AttractionDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val name: String,

    @SerializedName("rating")
    val rating: Float,

    @SerializedName("tag")
    val type: String,

    @SerializedName("icon")
    val icon: String,
)
