package com.inno.impl.data.local_models.map

import com.example.common.models.local.GeoPoint

data class City(
    val name: String,
    val points: List<GeoPoint>
)