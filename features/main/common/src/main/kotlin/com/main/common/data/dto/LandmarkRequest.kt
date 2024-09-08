package com.main.common.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkRequest(
    @SerializedName("uid")
    val id: String
)