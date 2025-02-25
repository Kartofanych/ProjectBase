package com.attraction.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkRequest(
    @SerializedName("uid")
    val id: String
)