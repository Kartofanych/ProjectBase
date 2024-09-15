package com.example.multimodulepractice.common.data.models.network

import androidx.annotation.Keep
import com.example.multimodulepractice.common.data.models.local.GeoPoint
import com.google.gson.annotations.SerializedName

@Keep
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