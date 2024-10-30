package com.splash.impl.data.models.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LaunchRequest(
    @SerializedName("version")
    val version: String
)
