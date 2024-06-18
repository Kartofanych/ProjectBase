package com.inno.impl.ui.fragments.map


data class MapUiState(
    val currentLandmarkId: String?,
    val state: MapState,
) {

    sealed interface MapState {
        object Error : MapState
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            currentLandmarkId = null,
            state = MapState.Loading
        )
    }

}
