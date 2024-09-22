package com.search_filters.impl.ui

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.FilterDistance
import com.example.multimodulepractice.common.data.models.local.FilterDistance.Companion.toFiltersValue
import com.search_filters.api.data.domain.SearchFilterRepository
import com.search_filters.api.data.models.SearchFilters
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

class SearchFiltersViewModel @Inject constructor(
    private val searchFilterRepository: SearchFilterRepository
) : ViewModel() {

    private val _uiEvent = Channel<SearchFiltersEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow by lazy {
        MutableStateFlow(SearchFiltersUiState(searchFilterRepository.filters.value.cities))
    }
    val uiStateFlow: StateFlow<SearchFiltersUiState>
        get() = _uiStateFlow.asStateFlow()

    val distanceStateFlow: MutableFloatState = mutableFloatStateOf(400f)
    val starsStateFlow: MutableFloatState = mutableFloatStateOf(400f)

    init {
        searchFilterRepository.filters.value.let {
            distanceStateFlow.floatValue = it.distance.toFiltersValue()
            starsStateFlow.floatValue = it.ratingFrom.toStarsValue()
        }
    }

    fun onAction(action: SearchFiltersAction) {
        when (action) {
            is SearchFiltersAction.OnDistanceChanged -> {
                distanceStateFlow.floatValue = action.value
            }

            is SearchFiltersAction.OnStarChanged -> {
                starsStateFlow.floatValue = action.value
            }

            is SearchFiltersAction.CityStateChanged -> {
                val newList = _uiStateFlow.value.cities.toMutableList()
                newList[action.index] =
                    newList[action.index].copy(isActive = !newList[action.index].isActive)
                _uiStateFlow.update {
                    it.copy(cities = newList)
                }
            }

            is SearchFiltersAction.OnClose -> {
                viewModelScope.launch {
                    if (action.withUpdates) {
                        searchFilterRepository.updateFilters(
                            SearchFilters(
                                cities = _uiStateFlow.value.cities,
                                distance = FilterDistance.fromFiltersValue(distanceStateFlow.floatValue),
                                ratingFrom = starsStateFlow.floatValue.toRatingFrom()
                            )
                        )
                    }
                    _uiEvent.send(SearchFiltersEvent.OnClose)
                }
            }

            SearchFiltersAction.OnZeroFilters -> {
                distanceStateFlow.floatValue = DEFAULT_DISTANCE_STATE
                starsStateFlow.floatValue = DEFAULT_STARS_STATE
                _uiStateFlow.update { state ->
                    state.copy(cities = state.cities.map { it.copy(isActive = true) })
                }
            }
        }
    }

    private fun Float.toRatingFrom(): Float {
        return abs((this / 100) - 5)
    }

    private fun Float.toStarsValue(): Float {
        return abs(this - 5) * 100
    }

    private companion object {
        const val DEFAULT_DISTANCE_STATE = 400f
        const val DEFAULT_STARS_STATE = 200f
    }
}
