package com.inno.impl.ui.map

data class MapUiState(
    val state: MapState,
) {

    sealed interface MapState {
        object Error : MapState
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            state = MapState.Loading
        )
    }

}
