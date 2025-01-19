package com.list.impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.common.data.models.local.ResponseState
import com.example.travelling.common.utils.Analytics
import com.list.impl.data.PromoListInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoListViewModel @Inject constructor(
    private val interactor: PromoListInteractor
) : ViewModel() {

    private val _uiEvent = Channel<PromoListEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiStateFlow = MutableStateFlow<PromoListUiState>(PromoListUiState.Loading)
    val uiStateFlow: StateFlow<PromoListUiState>
        get() = _uiStateFlow

    init {
        Analytics.reportOpenFeature("promo.list")
        getPromoCodes()
    }

    private fun getPromoCodes() {
        _uiStateFlow.update { PromoListUiState.Loading }
        viewModelScope.launch {
            when (val result = interactor.getPromoCodes()) {
                is ResponseState.Error -> _uiStateFlow.update { PromoListUiState.Error }
                is ResponseState.Success -> _uiStateFlow.update { PromoListUiState.Content(list = result.data.list) }
            }
        }
    }

    fun onAction(action: PromoListAction) {
        when (action) {
            is PromoListAction.OnOpenPromoItem -> {
                viewModelScope.launch {
                    _uiEvent.send(PromoListEvent.OnOpenPromo(action.id))
                }
            }

            PromoListAction.Close -> {
                viewModelScope.launch {
                    _uiEvent.send(PromoListEvent.OnClose)
                }
            }

            PromoListAction.OnReload -> getPromoCodes()
        }
    }
}
