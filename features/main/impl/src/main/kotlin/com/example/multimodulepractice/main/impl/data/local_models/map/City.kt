package com.example.multimodulepractice.main.impl.data.local_models.map

import com.example.multimodulepractice.common.models.local.GeoPoint

data class City(
    val name: String,
    val points: List<GeoPoint>
)