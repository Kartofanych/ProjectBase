package com.main.common.data.local.map

import com.example.multimodulepractice.common.models.local.GeoPoint

data class City(
    val name: String,
    val points: List<GeoPoint>
)