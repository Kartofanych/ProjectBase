package com.inno.impl.ui.map

import com.inno.landmark.ui.LandMarkState

data class MapUiState(
    val currentLandmarkState: LandMarkState?,
    val state: MapState,
) {

    sealed interface MapState {
        object Error : MapState
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            currentLandmarkState = null,
            state = MapState.Loading
        )
    }

}
