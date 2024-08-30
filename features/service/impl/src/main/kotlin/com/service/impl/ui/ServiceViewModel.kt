package com.service.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multimodulepractice.common.models.local.ResponseState
import com.example.multimodulepractice.common.utils.runWithMinTime
import com.service.impl.di.ServiceScope
import com.service.impl.domain.ServiceInteractor
import com.service.impl.ui.ServiceUiState.DataState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@ServiceScope
class ServiceViewModel @Inject constructor(
    private val serviceInteractor: ServiceInteractor,
    private val serviceId: String
) : ViewModel() {

    private val _uiEvent = Channel<ServiceUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow(ServiceUiState(state = DataState.Loading))
    val uiStateFlow: StateFlow<ServiceUiState>
        get() = _uiStateFlow

    init {
        getService()
    }

    private fun getService() {
        _uiStateFlow.update { ServiceUiState(state = DataState.Loading) }
        viewModelScope.launch {
            when (val result = runWithMinTime({ serviceInteractor.service(serviceId) })) {
                is ResponseState.Error -> _uiStateFlow.update { ServiceUiState(state = DataState.Error) }
                is ResponseState.Success -> _uiStateFlow.update {
                    ServiceUiState(state = DataState.Content(result.data))
                }
            }
        }
    }

    fun onServiceAction(action: ServiceAction) {
        when (action) {
            ServiceAction.OnBackPressed -> {
                viewModelScope.launch {
                    _uiEvent.send(ServiceUiEvent.OnBackPressed)
                }
            }

            ServiceAction.OnReload -> {
                getService()
            }
        }
    }
}
