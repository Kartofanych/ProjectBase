package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ImageDto(
    @SerializedName("url")
    val url: String
)