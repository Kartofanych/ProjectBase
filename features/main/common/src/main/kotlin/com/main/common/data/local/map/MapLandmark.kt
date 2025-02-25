package com.main.common.data.local.map

import com.example.travelling.common.data.models.local.GeoPoint

data class MapLandmark(
    val id: String,
    val name: String,
    val geoPoint: GeoPoint,
    val icon: String,
    val color: Int,
    val distance: Float,
    val categoryIds: List<String>
)