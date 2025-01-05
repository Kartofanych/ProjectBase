package com.example.travelling.geo.repository

import com.example.travelling.common.data.models.local.GeoPoint
import com.example.travelling.geo.models.GeoInfo
import com.example.travelling.geo.models.GeoInfoDto
import com.example.travelling.geo.models.GeoInfoDto.GeoPointDto

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