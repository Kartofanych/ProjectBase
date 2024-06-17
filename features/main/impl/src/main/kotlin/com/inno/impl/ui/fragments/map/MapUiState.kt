package com.inno.impl.ui.fragments.map

import com.example.common.models.GeoPoint

data class MapUiState(
    val location: GeoPoint,
    val currentLandmarkId: String?
) {
    companion object {
        fun EMPTY(geoPoint: GeoPoint): MapUiState = MapUiState(
            geoPoint,
            currentLandmarkId = null
        )
    }
}