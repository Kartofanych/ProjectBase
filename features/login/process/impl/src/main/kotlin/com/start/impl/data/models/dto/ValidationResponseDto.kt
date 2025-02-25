package com.start.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ValidationResponseDto(
    @SerializedName("token")
    val token: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("icon")
    val icon: String,
)
