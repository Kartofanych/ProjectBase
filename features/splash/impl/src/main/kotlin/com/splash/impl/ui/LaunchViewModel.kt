package com.splash.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.domain.DeeplinkHandler
import com.example.travelling.common.utils.Analytics
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.di.LaunchScope
import com.splash.impl.domain.LaunchInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@LaunchScope
class LaunchViewModel @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val citiesRepository: CitiesRepository,
    private val filtersRepository: FiltersRepository,
    private val deeplinkHandler: DeeplinkHandler,
) : ViewModel() {

    private val _uiEvent = Channel<LaunchEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<LaunchUiState>(LaunchUiState.Loading)
    val uiStateFlow: StateFlow<LaunchUiState>
        get() = _uiStateFlow

    init {
        Analytics.reportOpenFeature("launch")
        launch()
    }

    private fun launch() {
        _uiStateFlow.update { LaunchUiState.Loading }
        viewModelScope.launch {
            when (val result = launchInteractor.launch()) {
                is ResponseState.Error.OldVersion -> {
                    _uiStateFlow.update { LaunchUiState.OldVersion }
                }

                is ResponseState.Error -> {
                    _uiStateFlow.update { LaunchUiState.Error }
                }

                is ResponseState.Success -> {
                    citiesRepository.updateCities(result.data.cities)
                    filtersRepository.setDefaultFilters(result.data.filters)
                    _uiEvent.send(LaunchEvent.Start)
                }
            }
        }
    }

    fun onSplashAction(action: LaunchAction) {
        when (action) {
            LaunchAction.Update -> {
                deeplinkHandler.handleDeeplink("https://quick-travel.ru")
            }

            LaunchAction.Reload -> launch()
        }
    }
}
