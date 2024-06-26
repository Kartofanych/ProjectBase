package com.inno.impl.data.network.models.response

import com.google.gson.annotations.SerializedName

data class LandmarkAudioGidDto(
    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("time")
    val time: Long,

    @SerializedName("icon")
    val icon: String?
)
