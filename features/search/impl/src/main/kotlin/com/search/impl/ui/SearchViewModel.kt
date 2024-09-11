package com.search.impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<SearchEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(SearchUiState())
    val uiStateFlow: StateFlow<SearchUiState>
        get() = _uiStateFlow

    fun onAction(searchAction: SearchAction) {
        when (searchAction) {
            else -> Unit
        }
    }
}