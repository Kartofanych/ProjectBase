package com.example.travelling.main.impl.ui.map

data class MapUiState(
    val state: MapState,
    val isFiltersDefault: Boolean,
    val isMapOpen: Boolean
) {

    sealed interface MapState {
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            state = MapState.Content,
            isFiltersDefault = true,
            isMapOpen = false
        )
    }
}
