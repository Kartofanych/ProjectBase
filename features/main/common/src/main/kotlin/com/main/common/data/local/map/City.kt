package com.main.common.data.local.map

import com.example.travelling.common.data.models.local.GeoPoint

data class City(
    val name: String,
    val points: List<GeoPoint>
)