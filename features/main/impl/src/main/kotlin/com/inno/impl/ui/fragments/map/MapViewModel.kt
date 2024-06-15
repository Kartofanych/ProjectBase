package com.inno.impl.ui.fragments.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inno.impl.data.interactors.MapInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val interactor: MapInteractor
) : ViewModel() {

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(MapUiState.EMPTY)
    val uiStateFlow: StateFlow<MapUiState>
        get() = _uiStateFlow

    init {
        //Just example
        viewModelScope.launch {
            interactor.getMapInfo()
        }
    }

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