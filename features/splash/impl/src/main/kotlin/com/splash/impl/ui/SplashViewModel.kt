package com.splash.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.di.SplashScope
import com.splash.impl.domain.LaunchInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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

    init {
        launch()
    }

    private fun launch() {
        viewModelScope.launch {
            when (val result = launchInteractor.launch()) {
                is ResponseState.Error -> {
                    launch()
                }

                is ResponseState.Success -> {
                    citiesRepository.updateCities(result.data.cities)
                    filtersRepository.setDefaultFilters(result.data.filters)
                    viewModelScope.launch {
                        _uiEvent.send(SplashEvent.Start)
                    }
                }
            }
        }
    }
}
