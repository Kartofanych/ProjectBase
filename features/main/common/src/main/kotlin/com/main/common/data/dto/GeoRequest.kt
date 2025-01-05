package com.main.common.data.dto

import androidx.annotation.Keep
import com.example.travelling.common.data.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

@Keep
data class GeoRequest(
    @SerializedName("coordinates")
    val geoPoint: GeoPointDto
)