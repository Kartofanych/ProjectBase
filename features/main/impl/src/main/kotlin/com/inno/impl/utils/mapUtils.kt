package com.inno.impl.utils

import com.example.common.models.local.GeoPoint
import com.yandex.mapkit.geometry.Point

fun GeoPoint.toMapKitPoint(): Point {
    return Point(lat.toDouble(), lon.toDouble())
}
