package com.example.multimodulepractice.common.utils

import com.example.multimodulepractice.common.models.local.GeoPoint
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun calculateDistance(geoPoint1: GeoPoint, geoPoint2: GeoPoint): Double {
    val r = 6371.0
    val dLat = Math.toRadians(geoPoint2.lat.toDouble() - geoPoint1.lat.toDouble())
    val dLon = Math.toRadians(geoPoint2.lon.toDouble() - geoPoint1.lon.toDouble())
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(geoPoint1.lat.toDouble())) * cos(Math.toRadians(geoPoint2.lat.toDouble())) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return r * c
}