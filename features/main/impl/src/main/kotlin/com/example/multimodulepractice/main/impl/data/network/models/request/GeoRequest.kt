package com.example.multimodulepractice.main.impl.data.network.models.request

import androidx.annotation.Keep
import com.example.multimodulepractice.common.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
data class GeoRequest(
    @SerializedName("coordinates")
    val geoPoint: GeoPointDto
)