package com.filters.impl.ui

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.FilterDistance
import com.filters.api.data.FiltersRepository
import com.filters.api.data.models.Filters
import com.filters.impl.di.FiltersScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@FiltersScope
class FiltersViewModel @Inject constructor(
    private val filtersRepository: FiltersRepository
) : ViewModel() {

    private val _uiEvent = Channel<FiltersUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(FiltersUiState.EMPTY)
    val uiStateFlow: StateFlow<FiltersUiState>
        get() = _uiStateFlow.asStateFlow()

    val distanceStateFlow: MutableFloatState = mutableFloatStateOf(400f)

    init {
        filtersRepository.filters.value?.let {
            _uiStateFlow.value = FiltersUiState(categories = it.categories)
            distanceStateFlow.floatValue = it.distance.asFloat()
        }
    }

    fun onFiltersAction(action: FiltersAction) {
        when (action) {
            is FiltersAction.OnDistanceChanged -> {
                distanceStateFlow.floatValue = action.value
            }

            is FiltersAction.OnCategoryClicked -> {
                _uiStateFlow.update {
                    val category = action.category
                    val list = it.categories.toMutableList()
                    list[category.index] = category.copy(isSelected = !category.isSelected)
                    it.copy(categories = list)
                }
            }

            is FiltersAction.OnClose -> {
                viewModelScope.launch {
                    if (action.withUpdates) {
                        filtersRepository.updateFilters(
                            Filters(
                                uiStateFlow.value.categories,
                                distanceStateFlow.floatValue.asDistance()
                            )
                        )
                    }
                    _uiEvent.send(FiltersUiEvent.OnClose)
                }
            }

            FiltersAction.OnZeroFilters -> {
                _uiStateFlow.update {
                    FiltersUiState(filtersRepository.zeroFilters.categories)
                }
                distanceStateFlow.floatValue = DEFAULT_DISTANCE
            }
        }
    }

    private fun Float.asDistance(): FilterDistance {
        return when (this) {
            0f -> FilterDistance.M500
            100f -> FilterDistance.M1000
            200f -> FilterDistance.M2000
            300f -> FilterDistance.M5000
            else -> FilterDistance.EVERYWHERE
        }
    }

    private fun FilterDistance.asFloat(): Float {
        return when (this) {
            FilterDistance.M500 -> 0f
            FilterDistance.M1000 -> 100f
            FilterDistance.M2000 -> 200f
            FilterDistance.M5000 -> 300f
            FilterDistance.EVERYWHERE -> 400f
        }
    }

    private companion object {
        const val DEFAULT_DISTANCE = 400f
    }
}