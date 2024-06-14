package com.inno.impl.ui.fragments.map

data class MapUiState(
    val currentLandmarkId: String?
) {
    companion object {
        val EMPTY = MapUiState(
            currentLandmarkId = null
        )
    }
}