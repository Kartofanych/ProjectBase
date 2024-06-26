package com.inno.impl.data.local_models.map

import com.example.common.models.local.GeoPoint

data class MapLandmark(
    val id: String,
    val name: String,
    val geoPoint: GeoPoint,
    val icon: String,
    val color: Int
)