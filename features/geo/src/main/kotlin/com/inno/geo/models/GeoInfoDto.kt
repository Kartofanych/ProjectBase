package com.inno.geo.models

import kotlinx.serialization.Serializable

@Serializable
internal data class GeoInfoDto(
    val currentPoint: GeoPointDto
) {
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