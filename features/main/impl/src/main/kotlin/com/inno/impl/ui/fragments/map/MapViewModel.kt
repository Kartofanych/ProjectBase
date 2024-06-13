package com.inno.impl.ui.fragments.map

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY)
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    fun onMapAction(action: MapActions) {
        when (action) {
            MapActions.ModalDismissed -> {
                _uiStateFlow.update {
                    it.copy(currentLandmarkId = null)
                }
            }

            MapActions.OnPlaceMarkTapped -> {
                _uiStateFlow.update {
                    it.copy(currentLandmarkId = "123")
                }
            }
        }
    }

}