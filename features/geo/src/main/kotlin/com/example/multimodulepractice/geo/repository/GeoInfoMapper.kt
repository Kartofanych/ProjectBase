package com.example.multimodulepractice.geo.repository

import com.example.multimodulepractice.common.data.models.local.GeoPoint
import com.example.multimodulepractice.geo.models.GeoInfo
import com.example.multimodulepractice.geo.models.GeoInfoDto
import com.example.multimodulepractice.geo.models.GeoInfoDto.GeoPointDto

internal fun GeoPoint.toGeoPointDto(): GeoPointDto {
    return GeoPointDto(
        lat, lon
    )
}

internal fun GeoPointDto.toGeoPoint(): GeoPoint {
    return GeoPoint(
        lat, lon
    )
}

internal fun GeoInfoDto.toGeoInfo(): GeoInfo {
    return GeoInfo(
        currentPoint = currentPoint.toGeoPoint()
    )
}