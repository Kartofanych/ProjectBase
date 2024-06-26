package com.inno.impl.data.network.models.request

import com.google.gson.annotations.SerializedName

data class LandmarkRequest(
    @SerializedName("uid")
    val id: String
)