package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ImageDto(
    @SerializedName("url")
    val url: String
)