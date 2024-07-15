package com.example.multimodulepractice.main.impl.data.network.models.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LandmarkAudioGidDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("name")
    val title: String,

    @SerializedName("time")
    val time: Long,

    @SerializedName("icon")
    val icon: String?
)
