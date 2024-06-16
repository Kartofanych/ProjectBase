package com.inno.impl.data.network.models.request

import com.example.common.models.GeoPoint
import com.google.gson.annotations.SerializedName

data class GeoPointDto(

    @SerializedName("lat")
    val lat: Float,

    @SerializedName("lon")
    val lon: Float,

    ) {
    companion object {
        fun fromGeoPoint(point: GeoPoint): GeoPointDto {
            return GeoPointDto(
                lat = point.lat,
                lon = point.lon
            )
        }
    }
}