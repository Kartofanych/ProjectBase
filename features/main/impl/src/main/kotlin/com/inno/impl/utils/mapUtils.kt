package com.inno.impl.utils

import com.example.common.models.local.GeoPoint
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.TextStyle

fun GeoPoint.toMapKitPoint(): Point {
    return Point(lat.toDouble(), lon.toDouble())
}

fun iconTextStyle(textColor: Int) = TextStyle(
    8f,
    textColor,
    1f,
    null,
    TextStyle.Placement.RIGHT,
    4f,
    true,
    false
)
