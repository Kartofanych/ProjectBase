package com.splash.impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.filters.api.data.FiltersRepository
import com.splash.api.domain.CitiesRepository
import com.splash.impl.data.models.local.LaunchResponse
import com.splash.impl.di.SplashScope
import com.splash.impl.domain.LaunchInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.net.ConnectException
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
                    val data = result.data as LaunchResponse.Error
                    when (data.err) {
                        is ConnectException -> {
                            _uiEvent.send(SplashEvent.Error(ErrorType.INTERNET_ERROR))
                        }
                        else -> {
                            _uiEvent.send(SplashEvent.Error(ErrorType.APP_VERSION_ERROR))
                        }
                    }
                }

                is ResponseState.Success -> {
                    val data = result.data as LaunchResponse.Success
                    citiesRepository.updateCities(data.cities)
                    filtersRepository.setDefaultFilters(data.filters)
                    viewModelScope.launch {
                        _uiEvent.send(SplashEvent.Start)
                    }
                }
            }
        }
    }
}
