package com.filters.impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filters.api.data.FiltersRepository
import com.filters.impl.di.FiltersScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FiltersScope
class FiltersViewModel @Inject constructor(
    private val filtersRepository: FiltersRepository
) : ViewModel() {

    private val _uiEvent = Channel<FiltersUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<FiltersUiState>(FiltersUiState())
    val uiStateFlow: StateFlow<FiltersUiState>
        get() = _uiStateFlow

    init {
        collectFilters()
    }

    private fun collectFilters() {
        viewModelScope.launch {
            filtersRepository.filters.drop(1).collectLatest {
                Log.d("121212", "new filters")
            }
        }
    }

    fun onFiltersAction(action: FiltersAction) {}
}