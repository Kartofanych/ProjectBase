package com.example.travelling.main.impl.utils

import android.graphics.Color
import com.example.travelling.common.data.models.local.GeoPoint
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.TextStyle

fun GeoPoint.toMapKitPoint(): Point {
    return Point(lat.toDouble(), lon.toDouble())
}

fun Point.toGeoPoint(): GeoPoint {
    return GeoPoint(latitude.toFloat(), longitude.toFloat())
}

fun landmarkTextStyle(textColor: Int) = TextStyle(
    8f,
    textColor,
    3f,
    Color.parseColor("#FFFFFF"),
    TextStyle.Placement.BOTTOM,
    4f,
    true,
    false
)

val cityTextStyle = TextStyle(
    9f,
    Color.parseColor("#000000"),
    3f,
    Color.parseColor("#FFFFFF"),
    TextStyle.Placement.BOTTOM,
    4f,
    true,
    false
)
