package com.inno.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<GuideUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(GuideUiState.EMPTY)
    val uiStateFlow: StateFlow<GuideUiState>
        get() = _uiStateFlow

    fun launch(landmarkId: String) {

    }

    fun onGuideAction(action: GuideAction) {
        when (action) {
            GuideAction.OnBackPressed -> {
                viewModelScope.launch {
                    _uiEvent.send(GuideUiEvent.OnBackClicked)
                }
            }
        }
    }

}