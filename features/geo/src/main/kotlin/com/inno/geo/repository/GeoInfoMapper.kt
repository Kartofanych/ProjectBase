package com.inno.geo.repository

import com.example.common.models.local.GeoPoint
import com.inno.geo.models.GeoInfo
import com.inno.geo.models.GeoInfoDto
import com.inno.geo.models.GeoInfoDto.GeoPointDto

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