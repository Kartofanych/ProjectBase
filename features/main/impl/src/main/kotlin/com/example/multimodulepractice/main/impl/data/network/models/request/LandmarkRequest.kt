package com.example.multimodulepractice.main.impl.data.network.models.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkRequest(
    @SerializedName("uid")
    val id: String
)