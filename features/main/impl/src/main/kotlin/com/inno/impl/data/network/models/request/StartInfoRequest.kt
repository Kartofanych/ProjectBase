package com.inno.impl.data.network.models.request

import com.google.gson.annotations.SerializedName

data class StartInfoRequest(
    @SerializedName("geo_point")
    val geoPoint: GeoPointDto
)