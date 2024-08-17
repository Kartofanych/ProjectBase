package com.example.multimodulepractice.main.impl.ui.map

data class MapUiState(
    val state: MapState,
    val isFiltersDefault: Boolean
) {

    sealed interface MapState {
        object Loading : MapState
        object Content : MapState
    }


    companion object {
        fun EMPTY(): MapUiState = MapUiState(
            state = MapState.Content,
            isFiltersDefault = true
        )
    }
}
