package com.splash.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.data.models.local.ResponseState
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.di.SplashScope
import com.splash.impl.domain.LaunchInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@SplashScope
class SplashViewModel @Inject constructor(
    private val launchInteractor: LaunchInteractor,
    private val citiesRepository: CitiesRepository,
    private val filtersRepository: FiltersRepository
) : ViewModel() {

    private val _uiEvent = Channel<SplashEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiStateFlow: StateFlow<SplashUiState>
        get() = _uiStateFlow

    init {
        launch()
    }

    private fun launch() {
        _uiStateFlow.update { SplashUiState.Loading }
        viewModelScope.launch {
            when (val result = launchInteractor.launch()) {
                is ResponseState.Error.OldVersion -> {
                    _uiStateFlow.update { SplashUiState.OldVersion }
                }

                is ResponseState.Error -> {
                    delay(3000L)
                    launch()
                }

                is ResponseState.Success -> {
                    citiesRepository.updateCities(result.data.cities)
                    filtersRepository.setDefaultFilters(result.data.filters)
                    _uiEvent.send(SplashEvent.Start)
                }
            }
        }
    }

    fun onSplashAction(action: SplashAction) {
        when (action) {
            SplashAction.Update -> {
                _uiStateFlow.update {
                    // В будущем будет диплинк на обновление в маркете
                    SplashUiState.Loading
                }
            }
        }
    }
}
