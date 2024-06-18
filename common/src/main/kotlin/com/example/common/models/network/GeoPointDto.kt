package com.example.common.models.network

import com.example.common.models.local.GeoPoint
import com.google.gson.annotations.SerializedName

data class GeoPointDto(

    @SerializedName("latitude")
    val lat: Float,

    @SerializedName("longitude")
    val lon: Float

) {

    companion object {
        fun GeoPointDto.toLocalModel(): GeoPoint {
            return GeoPoint(
                lat = lat,
                lon = lon
            )
        }
    }
}