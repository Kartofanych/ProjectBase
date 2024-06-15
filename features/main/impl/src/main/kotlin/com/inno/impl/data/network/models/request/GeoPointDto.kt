package com.inno.impl.data.network.models.request

import com.google.gson.annotations.SerializedName

data class GeoPointDto(

    @SerializedName("lon")
    val lon: Float,

    @SerializedName("lat")
    val lat: Float
)