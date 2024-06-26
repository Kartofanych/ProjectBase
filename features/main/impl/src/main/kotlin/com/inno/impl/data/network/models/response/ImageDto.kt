package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url")
    val url: String
)