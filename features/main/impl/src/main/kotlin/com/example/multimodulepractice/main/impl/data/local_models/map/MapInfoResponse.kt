package com.example.multimodulepractice.main.impl.data.local_models.map

import com.filters.api.data.models.Filters

data class MapInfoResponse(
    val city: City,
    val list: List<MapLandmark>,
    val filters: Filters
)