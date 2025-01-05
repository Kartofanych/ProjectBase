package com.example.travelling.geo.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
internal data class GeoInfoDto(
    val currentPoint: GeoPointDto
) {
    @Keep
    @Serializable
    internal data class GeoPointDto(
        val lat: Float,
        val lon: Float
    )

    companion object {
        val EMPTY = GeoInfoDto(
            GeoPointDto(
                lat = 55.753685f, lon = 48.743305f
            )
        )
    }
}