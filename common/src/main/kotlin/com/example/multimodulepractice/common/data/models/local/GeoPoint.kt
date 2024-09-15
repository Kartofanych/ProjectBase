package com.example.multimodulepractice.common.data.models.local

import com.example.multimodulepractice.common.data.models.network.GeoPointDto

data class GeoPoint(val lat: Float, val lon: Float) {
    companion object {
        fun GeoPoint.toDto(): GeoPointDto {
            return GeoPointDto(
                lat = lat,
                lon = lon
            )
        }
    }
}
