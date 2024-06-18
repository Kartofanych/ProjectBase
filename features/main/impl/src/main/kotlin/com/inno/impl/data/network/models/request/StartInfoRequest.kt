package com.inno.impl.data.network.models.request

import com.example.common.models.network.GeoPointDto
import com.google.gson.annotations.SerializedName

data class StartInfoRequest(
    @SerializedName("coordinates")
    val geoPoint: GeoPointDto
)