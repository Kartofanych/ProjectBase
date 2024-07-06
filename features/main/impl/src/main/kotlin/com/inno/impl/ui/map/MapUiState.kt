package com.inno.impl.ui.map

import com.inno.landmark.ui.Landmark

data class MapUiState(
    val currentLandmarkState: Landmark,
    val state: MapState,
) {

    sealed interface MapState {
        object Error : MapState
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            currentLandmarkState = Landmark.EMPTY,
            state = MapState.Loading
        )
    }

}
