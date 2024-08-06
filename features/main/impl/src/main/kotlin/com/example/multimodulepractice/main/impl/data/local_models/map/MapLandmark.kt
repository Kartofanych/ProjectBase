package com.example.multimodulepractice.main.impl.data.local_models.map

import com.example.multimodulepractice.common.models.local.GeoPoint

data class MapLandmark(
    val id: String,
    val name: String,
    val geoPoint: GeoPoint,
    val icon: String,
    val color: Int,
    val distance: Float,
    val categoryIds: List<String>
)