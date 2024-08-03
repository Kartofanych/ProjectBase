package com.filters.impl.ui

import androidx.lifecycle.ViewModel
import com.filters.impl.di.FiltersScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@FiltersScope
class FiltersViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<FiltersUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<FiltersUiState>(FiltersUiState())
    val uiStateFlow: StateFlow<FiltersUiState>
        get() = _uiStateFlow

    fun onFiltersAction(action: FiltersAction) {}
}