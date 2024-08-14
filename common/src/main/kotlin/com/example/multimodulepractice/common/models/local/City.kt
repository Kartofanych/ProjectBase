package com.example.multimodulepractice.common.models.local

data class City(
    val id: String,
    val name: String,
    val geoPoint: GeoPoint,
    val radius: Float,
    val isLoaded: Boolean,
    val index: Int
)