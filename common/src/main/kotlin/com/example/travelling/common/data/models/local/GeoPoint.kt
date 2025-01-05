package com.example.travelling.common.data.models.local

import com.example.travelling.common.data.models.network.GeoPointDto

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
