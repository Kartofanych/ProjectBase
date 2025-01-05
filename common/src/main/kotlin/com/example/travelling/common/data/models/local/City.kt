package com.example.travelling.common.data.models.local

data class City(
    val id: String,
    val name: String,
    val geoPoint: GeoPoint,
    val radius: Float,
    val isLoaded: Boolean,
    val index: Int,
    val icon: String
)