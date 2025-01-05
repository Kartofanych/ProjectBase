package com.example.travelling.main.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.main.impl.data.models.MainTab
import com.example.travelling.main.impl.domain.MapScreenRepository
import com.main.common.di.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScope
class MainViewModel @Inject constructor(
    private val mapScreenRepository: MapScreenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState.EMPTY)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MainUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        mapScreenRepository.updateMapVisibility(true)
    }

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.OpenTab -> {
                _uiState.update {
                    it.copy(currentTab = action.tab)
                }
                viewModelScope.launch {
                    _uiEvent.send(MainUiEvent.OpenTab(action.tab))
                }
                mapScreenRepository.updateMapVisibility(action.tab == MainTab.MAP)
            }
        }
    }
}
