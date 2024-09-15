package com.attraction.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.attraction.impl.domain.AttractionInteractor
import com.example.multimodulepractice.common.data.models.local.ResponseState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AttractionViewModel @Inject constructor(
    private val attractionId: String,
    private val attractionInteractor: AttractionInteractor
) : ViewModel() {

    private val _uiEvent = Channel<AttractionEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<AttractionUiState>(AttractionUiState.Loading)
    val uiStateFlow: StateFlow<AttractionUiState>
        get() = _uiStateFlow

    init {
        loadAttraction()
    }

    private fun loadAttraction() {
        _uiStateFlow.update { AttractionUiState.Loading }

        viewModelScope.launch {
            when (val result = attractionInteractor.getLandmarkInfo(attractionId)) {
                is ResponseState.Error -> {
                    _uiStateFlow.update { AttractionUiState.Error }
                }

                is ResponseState.Success -> {
                    _uiStateFlow.update { AttractionUiState.Content(result.data) }
                }
            }
        }
    }


    fun onAction(action: AttractionAction) {
        when (action) {
            AttractionAction.OpenGuide -> {
                viewModelScope.launch {
                    _uiEvent.send(AttractionEvent.OpenGuide(attractionId))
                }
            }

            is AttractionAction.OpenService -> {
                viewModelScope.launch {
                    _uiEvent.send(AttractionEvent.OpenService(action.serviceId))
                }
            }

            AttractionAction.RecallAttraction -> loadAttraction()
        }
    }
}